package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            boolean isAnswer = true;
            Random rnd = new Random();
            String answer;
            while (!OUT.equals(line)) {
                log.add("User: " + line);
                isAnswer = !STOP.equals(line) && (CONTINUE.equals(line) || isAnswer);
                if (isAnswer) {
                    answer = answers.get(rnd.nextInt(answers.size()));
                    System.out.println(answer);
                    log.add("Bot: " + answer);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please enter 2 arguments:"
                    + " OUTPUT_FILE FILE_WITH_ANSWERS");
        }
        File answers = new File(args[1]);
        if (!answers.exists() || !answers.isFile()) {
            throw new IllegalArgumentException("Second parameter must be an existing file");
        }
    }

    public static void main(String[] args) {
        validate(args);
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}