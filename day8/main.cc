#include "../competitive.hpp"

typedef pair<int, int> cord;

void efficient(const vector<vector<int>>& trees) {
    const int rows = trees.size();
    const int cols = trees[0].size();
    set<cord> visible;

    // first pass, top and left
    vector<int> horiz(rows, 0); // horiz[i] == row[i]
    vector<int> vert(cols, 0);  // vert[i] == col[i]

    for (size_t i = 0; i < rows; i++) {
        horiz[i] = trees[i][0];
    }
    for (size_t i = 0; i < cols; i++) {
        vert[i] = trees[0][i];
    }
    // find any visible due to the left or the top, and adjust the tables accordingly
    for (size_t i = 1; i < rows - 1; i++) {
        for (size_t j = 1; j < cols - 1; j++) {
            int tree = trees[i][j];
            if (tree > horiz[i] || tree > vert[j]) {
                if (visible.find({i, j}) == visible.end()) {
                    visible.insert({i, j});
                }
                horiz[i] = max(horiz[i], tree);
                vert[j] = max(vert[j], tree);
            }
        }
    }

    // second pass, bottom and right
    for (size_t i = 0; i < rows; i++) {
        horiz[i] = trees[i][cols - 1];
    }
    for (size_t i = 0; i < cols; i++) {
        vert[i] = trees[rows - 1][i];
    }
    // find any visible due to the left or the top, and adjust the tables accordingly
    for (size_t i = rows - 2; i > 0; i--) {
        for (size_t j = cols - 2; j > 0; j--) {
            int tree = trees[i][j];
            if (tree > horiz[i] || tree > vert[j]) {
                if (visible.find({i, j}) == visible.end()) {
                    visible.insert({i, j});
                }
                horiz[i] = max(horiz[i], tree);
                vert[j] = max(vert[j], tree);
            }
        }
    }

    cout << visible.size() + (2 * rows) + (2 * cols) - 4 << endl;
}

void brute(const vector<vector<int>>& trees) {
    const int rows = trees.size();
    const int cols = trees[0].size();
    int count = 0;
    for (size_t row = 1; row < rows - 1; row++) {
        for (size_t col = 1; col < cols - 1; col++) {
            int tree = trees[row][col];

            // go left
            int maxHeight = 0;
            for (size_t left = 0; left < col; left++) {
                maxHeight = max(maxHeight, trees[row][left]);
            }
            if (tree > maxHeight) {
                count++;
                continue;
            }

            // go right
            maxHeight = 0;
            for (size_t right = col + 1; right < cols; right++) {
                maxHeight = max(maxHeight, trees[row][right]);
            }
            if (tree > maxHeight) {
                count++;
                continue;
            }

            // go up
            maxHeight = 0;
            for (size_t up = 0; up < row; up++) {
                maxHeight = max(maxHeight, trees[up][col]);
            }
            if (tree > maxHeight) {
                count++;
                continue;
            }

            // go down
            maxHeight = 0;
            for (size_t down = row + 1; down < rows; down++) {
                maxHeight = max(maxHeight, trees[down][col]);
            }
            if (tree > maxHeight) {
                count++;
                continue;
            }
        }
    }

    cout << count + rows * 2 + cols * 2 - 4 << endl;
}

int main() {
    vector<vector<int>> trees;

    string line;
    while(getline(cin, line)) {
        vector<int> tree;
        stringstream ss(line);
        char n;
        while(ss >> n) {
            n = n - '0';
            tree.push_back(n);
        }
        trees.push_back(tree);
    }

    efficient(trees);
    brute(trees);
}
