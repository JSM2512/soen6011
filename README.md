# Gamma Function Calculator

This project is a Gamma Function Calculator implemented in Java using Swing for the graphical user interface (GUI). It allows users to input a number and calculate its gamma function using Euler's infinite product formula.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [Running the Application (by IDE)](#running-the-application)
- [Accessibility](#accessibility)

## Overview

The Gamma Function Calculator is a simple tool that computes the gamma function for a given input number. It provides a GUI where users can input a number and see the calculated gamma value. The application also handles cases where the gamma function is not defined for the given input.

## Features

- Calculate the gamma function for positive numbers
- Handles cases where the gamma function is not defined
- User-friendly GUI
- Accessibility support using Java Accessibility API

## Requirements

- Java Development Kit (JDK) 8 or later
- IntelliJ IDEA (or any other Java IDE)
- Git

## Setup

1. **Clone the repository:**

   ```sh
   git clone https://github.com/JSM2512/soen6011.git
    ```
2. **Navigate to the project directory:**

    ```sh
     cd soen6011/src/main/java
    ```
3. **Compile the project using Java:**

    ```sh
     javac Gamma2.java
    ```
4. **Run the project using Java:**

    ```sh
     java Gamma2.java
    ```
## Running the Application (by IDE)

1. **Run the `Gamma2` class:**

   - In IntelliJ IDEA, locate the `Gamma2` class in the `main.java` package.
   - Right-click the `Gamma2` class and select `Run 'Gamma2.main()'`.

2. **Using the Application:**

   - A GUI window titled "Gamma Function Calculator" will appear.
   - Enter a number in the text field.
   - Click the "Calculate" button to compute the gamma function.
   - The result will be displayed below the input field.
   - Click the "Close" button to exit the application.

## Accessibility

The application aims to be accessible by setting accessible names and descriptions for Swing components using the Java Accessibility API. This ensures that screen readers and other assistive technologies can interact with the application effectively.

