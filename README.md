# markovtextgenerator

Java project for generating random text via Markov chains. The idea is to create a markov chain with each token or k-gram as a state, where  the next token/k-gram in the sequence is considered to build the transition probability matrix. 

One can experiment by using different files for training the model, and by varying the value of k in order to see which gives the most cohesive text, without just being a direct copy paste from the file.

## Usage

To compile the code: 
```bash
javac MarkovMatrix.java markovgenerator.java
```

To run the code once it has been compiled: 
```bash
java markovgenerator path_to_file k_value word_count
```
Where:
- path_to_file: Path to the file to be used as training for the markov model. Must be a text file. 
- k_value: How many words/tokens youd like to consider per k-gram.
- word_count: How many word/tokens you would like to generate.