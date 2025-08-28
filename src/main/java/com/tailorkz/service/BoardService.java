package com.tailorkz.service;

import com.tailorkz.model.Board;
import com.tailorkz.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class BoardService {
    private final static int BOARD_LIMIT = 9;

    public static Board createBoard(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Nenhum argumento fornecido, criando tabuleiro padrão.");
            return createDefaultBoard();
        }
        try {
            final var positions = Stream.of(args[0].split(" "))
                    .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
            return parseBoard(positions);
        } catch (Exception e) {
            System.out.println("Erro ao ler argumentos. Criando tabuleiro padrão. Erro: " + e.getMessage());
            return createDefaultBoard();
        }
    }

    private static Board parseBoard(Map<String, String> positions) {
        List<List<Space>> spaces = new ArrayList<>();
        for (int col = 0; col < BOARD_LIMIT; col++) {
            spaces.add(new ArrayList<>());
            for (int row = 0; row < BOARD_LIMIT; row++) {
                var positionConfig = positions.get("%s,%s".formatted(row, col));
                var expected = Integer.parseInt(positionConfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfig.split(",")[1]);
                spaces.get(col).add(new Space(expected, fixed));
            }
        }
        return new Board(spaces);
    }

    private static Board createDefaultBoard() {
        int[][] template = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int[][] solution = {
                {5,3,4,6,7,8,9,1,2}, {6,7,2,1,9,5,3,4,8}, {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3}, {4,2,6,8,5,3,7,9,1}, {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4}, {2,8,7,4,1,9,6,3,5}, {3,4,5,2,8,6,1,7,9}
        };

        List<List<Space>> spaces = new ArrayList<>();
        for (int col = 0; col < BOARD_LIMIT; col++) {
            spaces.add(new ArrayList<>());
            for (int row = 0; row < BOARD_LIMIT; row++) {
                int initialValue = template[row][col];
                int expectedValue = solution[row][col];
                boolean isFixed = initialValue != 0;
                spaces.get(col).add(new Space(expectedValue, isFixed));
            }
        }
        return new Board(spaces);
    }
}
