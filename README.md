# Smart Exam Seating & Invigilation Planner

A full-stack web application that automates exam seating arrangements for educational institutions.

## Problem Statement
Manual exam seating is time-consuming and error-prone. This system automates seat assignment ensuring students from the same branch never sit adjacent to each other.

## Tech Stack
- **Backend:** Java Spring Boot, Spring Data JPA, REST API
- **Frontend:** React.js, Axios
- **Database:** MySQL
- **Algorithm:** Greedy Interleaving (O(n log n))

## Features
- Add students with branch and year
- Add exam halls with rows and columns
- Add faculty invigilators
- Auto-generate seating plan with no same-branch adjacency
- Visual color-coded hall grid
- Export seating list as TXT and CSV

## Algorithm
Uses greedy interleaving to ensure students from different branches alternate seats — preventing copying and maintaining exam integrity.

## How to Run
1. Clone the repo
2. Set up MySQL database `examplanner`
3. Update `application.properties` with your DB credentials
4. Run `./mvnw spring-boot:run` for backend
5. Run `npm start` inside `/frontend` for frontend
