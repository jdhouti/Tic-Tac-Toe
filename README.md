# tic_tac_toe

A basic game of Tic Tac Toe written in Java.

## Getting Started

These instructions will help you setup the game on your own local computer. Please remember that this game does not support network gaming and thus it does not allow multiplayer other than local.

### Prerequisites

* Java must be installed on your computer
* A JDK must be installed using a unix shell.
```
$sudo apt-get install openjdk-7-jdk
```

### Installing

1. Download the entire repository onto your computer.
1. Navigate to the directory that you just downloaded using a Unix shell. This can be done using the `cd` command.
1. Compile the entire directory using `javac` such as:
   ```unix
   $javac *.java
   ```
1. Once this is done, use the `java` command to execute the *TicTacToe.java* file. Make sure that you are still inside the
correct directory when doing this.
   ```unix
   $java TicTacToe
   ```

## Features

* Supports 2 local players that must play on the same computer.
* A scoring system that keeps track of the amount wins, losses and ties for both players.
* Two different colors to represent each player's pieces.

## Built With

* [Eclipse](https://eclipse.org) - The IDE used
