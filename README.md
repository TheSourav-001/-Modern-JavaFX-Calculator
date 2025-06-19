# Modern JavaFX Calculator 
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)



A sleek, draggable desktop calculator built with JavaFX, featuring light and dark mode themes, comprehensive keyboard support, and smooth UI animations for an enhanced user experience.

---

## Table of Contents

- [Features](#features)  
- [Demo](#demo)  
- [Installation](#installation)  
- [Usage](#usage)  
- [Keyboard Shortcuts](#keyboard-shortcuts)  
- [Window Controls](#window-controls)  
- [System Requirements](#system-requirements)  
- [Project Structure](#project-structure)  
- [Build & Run](#build--run)  
- [Contributing](#contributing)  
- [Testing](#testing)  
- [Known Issues](#known-issues)  
- [Roadmap](#roadmap)  
- [License](#license)  
- [Contact](#contact)  

---

## Features

- Basic arithmetic operations: addition (+), subtraction (−), multiplication (×), and division (÷)  
- Advanced mathematical functions: square root (√) and square (x²)  
- Full keyboard support with intuitive shortcuts  
- Light and dark mode toggle for comfortable viewing  
- Smooth and responsive button animations  
- Custom draggable window with minimize and close functionality  
- Error handling and input validation  
- Clean and modular JavaFX codebase, easy to maintain and extend  

---

## Demo

![Calculator Light Theme](https://github.com/user-attachments/assets/bc0be65c-aecb-4964-b68b-cc93174fbc31)  
![Calculator Dark Theme](https://github.com/user-attachments/assets/49ea6acf-440e-4529-a254-80646ef97dc9)
---

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/TheSourav-001/modern-javafx-calculator.git
    cd modern-javafx-calculator
    ```

2. **Ensure Java Development Kit (JDK 8+) and JavaFX SDK are installed and configured in your environment.**

3. **Set the environment variable `PATH_TO_FX` to your JavaFX SDK lib path.**  
   Example (Linux/Mac):

    ```bash
    export PATH_TO_FX=/path/to/javafx-sdk/lib
    ```

4. **Compile and run the application:**  

    ```bash
    javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml src/com/example/calculator/Calculator.java
    java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml com.example.calculator.Calculator
    ```

---

## Usage

- Use the on-screen buttons or your keyboard to enter numbers and operators.  
- Click or press `=` / `Enter` to compute the result.  
- Use `C` or `Esc` to clear the current input.  
- Use `⌫` or `Backspace` to delete the last character.  
- Toggle dark/light theme using the sun/moon icon.  
- Drag the window by clicking and holding anywhere on the background.

---

## Keyboard Shortcuts

| Key           | Function                  |
|---------------|---------------------------|
| 0–9           | Number input              |
| +, −, *, /    | Arithmetic operations     |
| Enter or =    | Calculate result          |
| Delete / Esc  | Clear input               |
| Backspace     | Delete last character     |
| R             | Square root (√)           |
| S             | Square (x²)               |

---

## Window Controls

| Control       | Description               |
|---------------|---------------------------|
| − (Minimize)  | Minimize window           |
| × (Close)     | Close application         |
| ☀ / ☾         | Toggle light/dark theme   |

---

## System Requirements

- Java Development Kit (JDK) 8 or higher  
- JavaFX SDK (compatible with your JDK)  
- Operating System: Windows, macOS, Linux  

---

## Project Structure

```
modern-javafx-calculator/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── calculator/
│                       └── Calculator.java
├── resources/
│   ├── style.css
│   └── dark-style.css
├── README.md
├── LICENSE
└── .gitignore
```

---

## Build & Run

- Open the project in IntelliJ IDEA or any Java IDE configured with JavaFX support.  
- Build the project using your IDE's build tools or manually with the commands in the [Installation](#installation) section.  
- Run the `com.example.calculator.Calculator` class to launch the app.

---

## Contributing

Contributions are welcome! Please follow the steps below:

1. Fork the repository.  
2. Create a new branch (`git checkout -b feature/your-feature`).  
3. Make your changes and commit (`git commit -m 'Add your feature'`).  
4. Push to the branch (`git push origin feature/your-feature`).  
5. Open a Pull Request.

Please ensure code follows consistent style and includes comments where necessary.

---

## Testing

- Currently, manual testing is recommended due to the GUI nature of the application.  
- Future versions will include automated UI tests.

---

## Known Issues

- Expression parsing does not support parentheses or advanced math functions yet.  
- No history or memory functions implemented.  
- Limited localization support.

---

## Roadmap

- Add support for parentheses and advanced math operations (log, sin, cos).  
- Implement calculation history and memory storage.  
- Add localization and accessibility features.  
- Create automated UI and unit tests.  

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

**Developer:** Sourav Dipto  
**GitHub:** [TheSourav-001](https://github.com/TheSourav-001)  
**Email:** shouravdiptoshillapu@gmail.com

---

Thank you for using **Modern JavaFX Calculator**!  
Feel free to report issues or contribute to improve this project.
