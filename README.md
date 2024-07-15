# Microservices Project: Job, Company, and Review

## Overview

This project is a microservices-based application built with Spring Boot. It consists of three main services that work together to manage job listings, company profiles, and user reviews. The services communicate with each other using Eureka server for service discovery, and the data is stored in a PostgreSQL database.

## Services

### 1. Job Service
- **Purpose:** Manages job postings.
- **Functions:** Retrieve, Create, update, and delete job postings. Provide job details to other services.

### 2. Company Service
- **Purpose:** Manages company profiles.
- **Functions:** Retrieve, Create, update, and delete company profiles. Associate jobs with companies. Retrieve job listings from the Job Service. Retrieve reviews from the Review Service.

### 3. Review Service
- **Purpose:** Manages user reviews for companies and jobs.
- **Functions:** Retrieve, Create, update, and delete reviews. Associate reviews with companies and jobs. Provide review data to the Company and Job services.

## Architecture

- **Microservices:** Each service is an independent application that communicates over the network.
- **Eureka Server:** Acts as a service registry, enabling service discovery and communication.
- **PostgreSQL:** The database used for storing all data. Each service has its own tables.

## Interactions

1. **Job Service:**
   - Manages job postings.
   - Provides job details to the Company and Review services.

2. **Company Service:**
   - Manages company profiles.
   - Associates jobs with the company.
   - Retrieves job listings from the Job Service.
   - Retrieves reviews from the Review Service.

3. **Review Service:**
   - Manages user reviews.
   - Associates reviews with companies and jobs.
   - Provides review data to the Company and Job services.

## Example Scenario

1. A company registers on the platform and creates a profile in the Company Service.
2. The company posts a new job listing via the Job Service.
3. A user views the company profile and sees the job listings retrieved from the Job Service.
4. The user applies for a job and later leaves a review about the job and the company through the Review Service.
5. The company profile displays the review details retrieved from the Review Service.

## Technologies Used

- Spring Boot
- Eureka Server
- PostgreSQL
- Docker (optional)

### Prerequisites

- JDK 11 or higher
- Maven
- PostgreSQL
