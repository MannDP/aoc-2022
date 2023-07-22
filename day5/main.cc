#include <iostream>
#include <string>
#include <sstream>
#include <list>
#include <stack>
using namespace std;

int main(const int argc, const char* const argv[]) {
    // process initial state
    string line;
    getline(cin, line);

    const int numStacks = line.size() / 3;
    list<char> state[numStacks];
    for (size_t i = 0; i < numStacks; i++) {
        state[i] = list<char>{};
    }

    do {
        if (isdigit(line.at(1))) break;

        for (int stack = 0; stack < numStacks; stack++) {
            char letter = line[stack * 3 + stack + 1];
            if (letter != ' ') {
                state[stack].push_front(letter);
            }
        }
    } while (getline(cin, line));
    getline(cin, line);

    // process moves
    int amount, from, to;
    string muck;
    while(getline(cin, line)) {
        istringstream iss{line};
        iss >> muck >> amount >> muck >> from >> muck >> to;
        from -= 1;
        to -= 1;
        stack<char> moved;
        while (amount > 0) {
            char c = state[from].back();
            state[from].pop_back();
            moved.emplace(c);
            amount--;
        }

        while (!moved.empty()) {
            char c = moved.top();
            moved.pop();
            state[to].push_back(c);
        }
    }

    // print result
    for (size_t i = 0; i < numStacks; i++) {
        cout << state[i].back();
    }
    cout << endl;

    // // DEBUG
    // for (int stack = 0; stack < numStacks; stack++) {
    //     for (const auto& c : state[stack]) {
    //         cout << c << endl;
    //     }
    //     cout << "=====" << endl;
    // }
}
