package com.devassist.devassistai.service;

import com.devassist.devassistai.dto.DocumentQueryRequest;
import com.devassist.devassistai.dto.DocumentQueryResponse;
import com.devassist.devassistai.dto.DocumentUploadResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    private static final int CHUNK_SIZE = 1000;

    private static final int CHUNK_OVERLAP = 200;

    public DocumentService(VectorStore vectorStore, ChatClient chatClient){
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    public DocumentUploadResponse uploadDocument(MultipartFile file) throws IOException{

        String rawText = extractTextFromPdf(file);

        List<String> chunks = splitINtoChunks(rawText);

        List<Document> documents = chunks.stream()
                .map(chunk -> new Document(chunk))
                .collect(Collectors.toList());

        vectorStore.add(documents);

        return new DocumentUploadResponse(
                file.getOriginalFilename(),
                "Successfully uploaded and processed",
                chunks.size()
        );
    }

    public DocumentQueryResponse queryDocument(DocumentQueryRequest request){

        List<Document> relevantDocs = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(request.getQuestion())
                        .topK(4)
                        .build()
        );

        if(relevantDocs.isEmpty()){
            return new DocumentQueryResponse(
                    request.getQuestion(),
                    "No relevant information found in the uploaded documents."
            );
        }

        String context = relevantDocs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        String answer = chatClient
                .prompt()
                .system("""
                        You are a helpful assistant that answers questions\s
                        based only on the provided document context.
                        If the answer is not in the context, say\s
                        "I could not find this information in the document."
                        """)
                .user("Context:\n" + context + "\n\nQuestion: " + request.getQuestion())
                .call()
                .content();

        return new DocumentQueryResponse(request.getQuestion(), answer);

    }

    private String extractTextFromPdf(MultipartFile file) throws IOException{
        try (PDDocument pdDocument = org.apache.pdfbox.Loader.loadPDF(file.getBytes())){
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(pdDocument);
        }
    }

    private List<String>splitINtoChunks(String text){
        List<String> chunks = new ArrayList<>();
        int start = 0;

        while(start < text.length()){
            int end = Math.min(start + CHUNK_SIZE, text.length());
            chunks.add(text.substring(start, end));

            start += (CHUNK_SIZE- CHUNK_OVERLAP);
        }

        return chunks;
    }

}
