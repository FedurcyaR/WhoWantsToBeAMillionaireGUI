# Who Wants to Be a Millionaire? (GUI)

A Java Swing version of the "Who Wants to Be a Millionaire?" quiz game (package `loim`). It has two game modes, multiple-choice questions and ordering questions, lifelines, and a Top 10 hall of fame for each mode. The questions and the UI text are in Hungarian.

## Requirements

- JDK 11 or newer (developed with OpenJDK 25). A full JDK is needed, not only a JRE, because you compile the code.
- A desktop environment. The game uses Swing and cannot run headless.

Check your version with:

```bash
java -version
javac -version
```

## Running the program

The program reads and writes files via relative paths (`src/resources/...`). **Always run it from the project root**, the folder that contains `src/`.

### From the command line

```bash
# 1. Compile into a "bin" folder
mkdir -p bin
javac -encoding UTF-8 -d bin src/loim/*.java

# 2. Run
java -Dfile.encoding=UTF-8 -cp bin loim.Main
```

### From an IDE (IntelliJ IDEA, Eclipse, VS Code)

1. Open the project folder and mark `src` as the source root.
2. Run the `loim.Main` class.
3. Make sure the working directory is the project root, not `src/`. Otherwise the question files will not be found.

## Project layout

```
src/
├── loim/         Java sources (entry point: Main.java)
└── resources/
    ├── loim_tesztkerdesek.csv   multiple-choice questions
    ├── loim_sorbarakas.csv      ordering questions
    ├── tkTop10.txt              Top 10 for multiple-choice mode (serialized)
    └── srTop10.txt              Top 10 for ordering mode (serialized)
```

## Notes

- **Questions:** the CSV files are `;`-separated and UTF-8 encoded. You can add your own questions by following the existing format:
  - Multiple choice: `Nehézség;Kérdés;A;B;C;D;Válasz;Kategória`
  - Ordering: `Sorkérdés;A;B;C;D;Válasz;Kategória`
- **Top 10 files:** `tkTop10.txt` and `srTop10.txt` are Java-serialized binary files, not text. Do not edit them by hand. They are updated automatically when a player makes the list.
- **Troubleshooting:** if the console prints `Hiba!` followed by a message at startup, the question files could not be read. Check that you are running from the project root.
