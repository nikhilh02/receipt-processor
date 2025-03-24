# Receipt Processor

This is a Spring Boot application for processing receipts and calculating points based on defined rules. The app runs inside a Docker container and uses Redis for storing points with their corresponding receipt IDs.

## Clone the Repository

`git clone https://github.com/nikhilh02/receipt-processor.git` \
`cd <project-directory>`

## Run the Application with Docker Compose

`docker-compose up -d`

## Access the Application
Once the app is running, access it at: \
`http://localhost:8080`

## API Endpoints
- **Process Receipt:**
  - Endpoint: `POST /receipts/process`
- **Get Points:**
  - Endpoint: `GET /receipts/{id}/points`

## Stop the Application

`docker-compose down`

## Note
- The app runs on port **8080**.
- Redis runs on port **6379**.
