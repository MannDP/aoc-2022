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
                visible.insert({i, j});
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
                visible.insert({i, j});
                horiz[i] = max(horiz[i], tree);
                vert[j] = max(vert[j], tree);
            }
        }
    }

    cout << visible.size() + (2 * rows) + (2 * cols) - 4 << endl;
}

int treeScore(const vector<vector<int>>& trees, const int row, const int col) {
    const int rows = trees.size();
    const int cols = trees[0].size();
    if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) return 0;
    
    const int tree = trees[row][col];
    int left = col - 1, right = col + 1, up = row - 1, down = row + 1;

    while (trees[row][left] < tree && left > 0) left--;
    while (trees[row][right] < tree && right < rows - 1) right++;
    while (trees[up][col] < tree && up > 0) up--;
    while (trees[down][col] < tree && down < cols - 1) down++;
    
    return (col - left) * (right - col) * (row - up) * (down - row);
}

void bestView(const vector<vector<int>>& trees) {
    int res = 0;
    for (int i = 0; i < trees.size(); i++) {
        for (int j = 0; j < trees[i].size(); j++) {
            res = max(res, treeScore(trees, i, j));
        }
    }

    cout << res << endl;
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
    bestView(trees);
}
