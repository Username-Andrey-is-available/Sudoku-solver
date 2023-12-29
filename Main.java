class Main {

    public static void main(String[] args) {
        Main sudokuSolver = new Main();

        //Задаем начальное поле
        char[][] sudokuBoard = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','8','9','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        sudokuSolver.solveSudoku(sudokuBoard);

        printSudokuBoard(sudokuBoard);
    }

    private static void printSudokuBoard(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        // Начнем решение судоку с первой ячейки
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {
        // Базовый случай: если строка равна длине доски, значит, доска полностью заполнена
        if (row == board.length) {
            return true;
        }

        // Переходим к следующей строке, когда текущая полностью заполнена
        if (col == board[0].length) {
            return solve(board, row + 1, 0);
        }

        // Пропускаем ячейки, которые уже заполнены
        if (board[row][col] != '.') {
            return solve(board, row, col + 1);
        }

        // Попробуем разные числа в текущей ячейке
        for (char num = '1'; num <= '9'; num++) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num; // Заполняем текущую ячейку допустимым числом

                // Переходим к следующей ячейке
                if (solve(board, row, col + 1)) {
                    return true;
                }

                // Откатываемся к предыдущему состоянию, если решение не найдено
                board[row][col] = '.';
            }
        }

        // Допустимое решение не найдено
        return false;
    }

    private boolean isValidPlacement(char[][] board, int row, int col, char num) {
        // Проверяем, есть ли num уже в той же строке, столбце или подсетке 3x3
        for (int i = 0; i < board.length; i++) {
            // Проверяем строку
            if (board[i][col] == num) {
                return false;
            }

            // Проверяем столбец
            if (board[row][i] == num) {
                return false;
            }

            // Проверяем подсетку 3x3
            int subgridRow = 3 * (row / 3) + i / 3; // Вычисляем индекс строки подсетки
            int subgridCol = 3 * (col / 3) + i % 3; // Вычисляем индекс столбца подсетки

            if (board[subgridRow][subgridCol] == num) {
                return false;
            }
        }

        // Размещение допустимо
        return true;
    }
}
