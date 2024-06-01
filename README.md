# RedBlackTree Job Candidates Database

This repository contains a Java application implementing a Red-Black Tree data structure to manage job candidates. The Red-Black Tree ensures that the data structure remains balanced, providing efficient search, insert, and delete operations. The Candidate class holds detailed information about each job candidate, and the RedBlackTree class manages the tree structure.

## Features

- **Red-Black Tree Implementation**: Ensures balanced tree properties for efficient insertion and search operations.
- **Candidate Management**: Supports adding, searching, and managing job candidates with comprehensive attribute comparisons.
- **Custom Comparison**: The `Candidate` class implements `Comparable` to allow comparison based on multiple attributes.

## Candidate Class

The `Candidate` class represents a job candidate with the following attributes:

- `id`: Unique identifier
- `fullName`: Full name of the candidate
- `nationality`: Nationality of the candidate
- `city`: City of residence
- `latitude` and `longitude`: Geographical coordinates
- `gender`: Gender of the candidate
- `age`: Age of the candidate
- `englishGrade`, `mathGrade`, `sciencesGrade`, `languageGrade`: Grades in various subjects
- `portfolioRating`: Rating of the candidate's portfolio
- `coverLetterRating`: Rating of the candidate's cover letter
- `referenceLetterRating`: Rating of the candidate's reference letter

The `Candidate` class includes getters and setters for each attribute and implements the `compareTo` method to allow comparison based on these attributes.

## RedBlackTree Class

The `RedBlackTree` class is a generic implementation of a Red-Black Tree. It includes methods for inserting, searching, and iterating over the tree. The `Node` inner class represents the nodes of the tree.

### Key Methods

- `insert(T data)`: Inserts a new node with the specified data into the tree.
- `contains(Candidate candidate)`: Checks if the tree contains a candidate with the specified attributes.
- `iterator()`: Returns an iterator to traverse the tree in in-order sequence.
- `rotate(Node<T> child, Node<T> parent)`: Performs left or right rotations to maintain tree balance.

### Properties Enforcement

The `enforceRBTreePropertiesAfterInsert` method ensures that the Red-Black Tree properties are maintained after each insertion. This includes handling red-red violations and performing necessary rotations and color changes.

## Usage

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/RedBlackTreeJobCandidates.git

2. **Compile the Java files:**:
   ```bash
   javac rbt/*.java

3. **Run the application:**:
   ```bash
   java rbt.Main
