# Project Report: Smart City/Campus Scheduling
### Author: Ernar Sadenov, SE-2430
### Date: November 3, 2025

---

## Introduction
This project implements and analyzes key graph algorithms for scheduling tasks in a smart city or campus environment. The focus is on directed graphs with possible cyclic dependencies, critical path detection, and task ordering.

---

## Objectives
- Find Strongly Connected Components (SCC) using Tarjan's algorithm  
- Build the condensation graph (a DAG of SCCs)  
- Perform topological sorting on the DAG  
- Compute shortest paths from a source node  
- Determine the longest path (critical path) in the DAG  

---

## Input Data
Graphs are represented in JSON files categorized by size:
- Small (6-10 nodes), Medium (10-20 nodes), Large (20-50 nodes)  
- Each graph includes nodes, weighted directed edges, and a source vertex

---

## Methodology

| Algorithm               | Purpose                       | Complexity    |
|-------------------------|------------------------------|---------------|
| Tarjan's SCC            | Identify strongly connected components in directed graph | O(V + E)     |
| Condensation Generation | Construct DAG from SCCs       | O(V + E)      |
| Topological Sort (Kahn) | Order DAG nodes linearly      | O(V + E)      |
| Shortest Path (DP)      | Find shortest distances in DAG| O(V + E)      |
| Longest Path (DP + DFS) | Find critical path in DAG     | O(V + E)      |

---

## Results

### Strongly Connected Components
| SCC ID | Vertices     | Type   |
|--------|--------------|--------|
| 0      | [3, 2, 1]    | Cycle  |
| 1      | [0]          | Singleton |
| 2      | [7]          | Singleton |
| 3      | [6]          | Singleton |
| 4      | [5]          | Singleton |
| 5      | [4]          | Singleton |

### Condensation Graph
- Vertices: 6 (each representing an SCC)  
- Edges: 7  
- Acyclic: Yes (DAG)  

### Topological Order of SCCs
\[1, 5, 0, 4, 3, 2\]

### Shortest Paths from Source (SCC 5)
| SCC ID | Distance |
|--------|----------|
| 5      | 0        |
| 4      | 2        |
| 3      | 7        |
| 2      | 8        |
| 1      | ∞        |
| 0      | ∞        |

### Longest Path (Critical Path)
- Length: 8  
- Path (SCC IDs): 5 → 4 → 3 → 2  
- Corresponding Vertices: 4 → 5 → 6 → 7  

---

## Performance Metrics

| Task                   | Time (microseconds) |
|------------------------|---------------------|
| Tarjan's SCC Detection | ~25                 |
| Condensation & Toposort| ~15                 |
| Shortest Paths         | ~12                 |
| Longest Path           | ~12                 |
| **Total**              | **~64**             |

---

## Conclusions
- The implemented algorithms correctly identify cyclic dependencies and order tasks optimally.  
- Critical path identification defines minimum completion time for dependent tasks.  
- Performance meets requirements for medium-sized scheduling graphs.  

---

## Future Work
- Enhance visualization of results dynamically  
- Implement parallelized scheduling based on SCC and critical path  
- Expand input format for dynamic real-time task adjustments  

---

## References
- Tarjan's Algorithm, Bela Bollobás, "Modern Graph Theory"  
- Cormen et al., "Introduction to Algorithms"  
- Project and course materials

---

*This report was generated using project outputs and analysis by Ernar Sadenov SE-2430.*

