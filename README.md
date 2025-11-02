# Design and Analysis of Algorithms — Assignment 4  
### Author: Nurdaulet Usenbay  
**Course:** DAA (Design and Analysis of Algorithms)  
**University:** Astana IT University  

---

## Project Overview
This project implements a suite of advanced graph-based algorithms focused on **Directed Acyclic Graphs (DAGs)** and **Strongly Connected Components (SCCs)**.  
It provides analytical comparison, performance metrics, and automated dataset generation for testing scalability and efficiency.

### Implemented Algorithms
1. **Tarjan’s Algorithm** — detects Strongly Connected Components (SCCs) in a directed graph.  
2. **Topological Sort (Kahn’s Algorithm)** — determines linear ordering of vertices in a DAG.  
3. **DAG Shortest Path** — computes minimum cost paths for weighted acyclic graphs.  
4. **DAG Longest Path** — finds maximum path lengths (useful for scheduling and critical path analysis).  
5. **Dataset Generator** — automatically creates small, medium, and large test datasets as JSON files under `src/main/resources/data/`.

---

## Project Structure

- └── src/
- ├── main/
- │ ├── java/
- │ │ ├── graph/
- │ │ │ ├── Graph.java
- │ │ │ ├── tarjan/
- │ │ │ │ └── TarjanSCC.java
- │ │ │ ├── topo/
- │ │ │ │ └── TopologicalSort.java
- │ │ │ ├── dagsp/
- │ │ │ │ ├── DAGShortestPaths.java
- │ │ │ │ └── DAGLongestPaths.java
- │ │ └── data/
- │ │ └── DatasetGenerator.java
- │ └── resources/
- │ └── data/
- │ ├── small_graphs.json
- │ ├── medium_graphs.json
- │ └── large_graphs.json
- └── test/
- └── java/
- ├── TarjanSCCTest.java
- ├── TopologicalSortTest.java
- ├── DAGShortestPathsTest.java
- └── DAGLongestPathsTest.java
- ├── pom.xml
- ├── README.md

## Algorithm Analysis
- Algorithm	Time Complexity	Space Complexity	Key Feature
- Tarjan SCC	O(V + E)	O(V)	DFS stack based, linear time
- Topological Sort	O(V + E)	O(V)	Kahn’s Algorithm (queue based)
- DAG Shortest Path	O(V + E)	O(V)	Dynamic programming over topological order
- DAG Longest Path	O(V + E)	O(V)	Reverse relaxation for max distance

Learning Outcomes

- Understanding of graph traversal and SCC detection methods

- Implementation of topological ordering and DAG-based path analysis

- Dataset generation for empirical algorithm testing

- Comparison of algorithmic complexities and real-world performance
