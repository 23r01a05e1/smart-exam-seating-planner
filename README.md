# Smart Exam Seating & Invigilation Planner

A full-stack web application that automates exam seating arrangements for educational institutions by uploading bulk data via Excel files.

## Problem Statement
Manual exam seating is time-consuming and error-prone. This system automates seat assignment ensuring students from the same branch never sit adjacent to each other.

## Tech Stack
- **Backend:** Java Spring Boot, Spring Data JPA, REST API, Apache POI
- **Frontend:** React.js, Axios
- **Database:** MySQL
- **Algorithm:** Greedy Interleaving (O(n log n))

## Features
- 📂 Upload students list via Excel file
- 📂 Upload faculty/invigilator list via Excel file
- 📂 Upload halls list with different bench counts per hall via Excel file
- 🎯 Auto-generate seating plan with no same-branch adjacency
- 🎨 Visual color-coded hall grid
- 📄 Export seating list as TXT and CSV
- 🗑️ Delete and re-upload data anytime

## Excel File Formats

### students.xlsx
| Roll Number | Name | Branch | Year |
|---|---|---|---|
| 23CS001 | Ashwitha | CSE | 2nd Year |

### faculty.xlsx
| Name | Department |
|---|---|
| Dr. Priya Rao | CSE |

### halls.xlsx
| Hall Name | Number of Benches |
|---|---|
| Hall A | 40 |
| Room 101 | 35 |

## Algorithm
Uses greedy interleaving to ensure students from different branches alternate seats — preventing copying and maintaining exam integrity.

## How to Run
1. Clone the repo
2. Set up MySQL database `examplanner`
3. Update `application.properties` with your DB credentials
4. Run `./mvnw spring-boot:run` for backend
5. Run `npm start` inside `/frontend` for frontend
6. Upload Excel files for students, faculty and halls
7. Click Generate Seating Plan!
