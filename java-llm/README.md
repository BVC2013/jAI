# Java LLM Project

This project implements a simple large language model (LLM) using a neural network, all written in Java without external libraries. The model is designed to store user input for long-term context, allowing it to generate more relevant responses based on previous interactions.

## Project Structure

```
java-llm
├── src
│   ├── Main.java            # Entry point of the application
│   ├── NeuralNetwork.java   # Core functionality of the neural network
│   ├── LLM.java             # Represents the large language model
│   ├── storage
│   │   └── ContextStorage.java # Handles user context storage
│   └── utils
│       └── FileUtils.java   # Utility functions for file operations
├── data
│   └── user_context.txt     # Stores user input for long-term context
└── README.md                # Documentation for the project
```

## Setup Instructions

1. **Clone the Repository**: 
   Clone this repository to your local machine using:
   ```
   git clone <repository-url>
   ```

2. **Compile the Java Files**: 
   Navigate to the `src` directory and compile the Java files:
   ```
   cd java-llm/src
   javac *.java storage/*.java utils/*.java
   ```

3. **Run the Application**: 
   Execute the `Main` class to start the application:
   ```
   java Main
   ```

## Usage

- Upon running the application, you will be prompted to enter text. The neural network will process this input and generate a response.
- The user input will be stored in `data/user_context.txt` for future reference, allowing the model to maintain context across sessions.

## Overview of Components

- **Main.java**: Initializes the neural network and manages user input and context storage.
- **NeuralNetwork.java**: Implements the core functionality of the neural network, including training and prediction methods.
- **LLM.java**: Interacts with the `NeuralNetwork` class to process user input and generate responses.
- **ContextStorage.java**: Manages reading and writing of user context to the `user_context.txt` file.
- **FileUtils.java**: Provides utility functions for file operations, used by `ContextStorage`.

## License

This project is licensed under the MIT License. See the LICENSE file for details.