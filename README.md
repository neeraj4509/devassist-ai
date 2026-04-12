# DevAssist AI 🤖

An AI-powered developer productivity tool built with Spring Boot and Spring AI.
Provides intelligent assistance for Java code review, SQL query explanation,
and document Q&A using RAG (Retrieval Augmented Generation).

## 🚀 Features

- `/api/code/review` → Reviews Java code and provides feedback on bugs, improvements and best practices
- `/api/sql/explain` → Explains SQL queries in plain English with performance suggestions
- `/api/docs/upload` → Upload any technical PDF document for processing
- `/api/docs/ask` → Ask questions about uploaded documents using RAG pipeline

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.5
- Spring AI 1.1.2
- Ollama (llama3.2:1b) — Chat model
- Ollama (mxbai-embed-large) — Embedding model
- PostgreSQL + PGVector — Vector database
- Apache PDFBox — PDF text extraction
- Docker + Docker Compose
- Maven

## ⚙️ Prerequisites

- Java 17+
- Maven 3.9+
- Docker Desktop
- Ollama installed locally

## 🏃 Running Locally

### Step 1 — Pull Ollama models
```bash
ollama pull llama3.2:1b
ollama pull mxbai-embed-large
```

### Step 2 — Start PostgreSQL + PGVector
```bash
docker run -d --name pgvector \
  -e POSTGRES_DB=devassist \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=password \
  -p 5432:5432 \
  pgvector/pgvector:pg16
```

### Step 3 — Clone and run
```bash
git clone https://github.com/neeraj4509/devassist-ai.git
cd devassist-ai/devassistai
mvn spring-boot:run
```

### Step 4 — App runs at
http://localhost:8080

## 🐳 Running with Docker Compose
```bash
git clone https://github.com/neeraj4509/devassist-ai.git
cd devassist-ai/devassistai
docker-compose up --build
```

## 📌 API Endpoints

### Code Review
POST /api/code/review
Content-Type: application/json
{
"code": "public int add(int a, int b) { return a + b; }",
"language": "Java"
}

### SQL Explainer
POST /api/sql/explain
Content-Type: application/json
{
"query": "SELECT * FROM vehicles WHERE status = 1 AND model_year > 2020"
}

### Document Upload
POST /api/docs/upload
Content-Type: multipart/form-data
file: <your PDF file>

### Document Q&A
POST /api/docs/ask
Content-Type: application/json
{
"question": "What is this document about?"
}
