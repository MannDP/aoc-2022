#include "../competitive.hpp"

const size_t WIDTH = 40;
const size_t HEIGHT = 6;


void track(const int cycle, const int X, unordered_set<int>& aggr) {
    int pos = (cycle - 1) % WIDTH;
    int absDiff = abs(X - pos);
    if (absDiff <= 1) aggr.insert(cycle);
}

// there is no pipelining
int main(const int argc, const char* const argv[]) {
    // state
    int cycle = 1; // the currently executing cycle
    int X = 1;
    unordered_set<int> litCycles;

    string cmd;
    int V;
    // process (there is more than enough input)
    while (cycle <= WIDTH * HEIGHT) {
        cin >> cmd;
        if (cmd == "noop") {
            // do nothing this cycle, but handle the case where the sprite is already there
            track(cycle, X, litCycles);
            cycle += 1;
        } else {
            // this will update the sprite's position, but only after the END of the next cycle
            track(cycle, X, litCycles);
            cycle += 1;
            track(cycle, X, litCycles);
            cin >> V;
            X += V;
            // cout << "At end of cycle: " << cycle << ", X is now: " << X << endl;
            cycle += 1;
        }
    }

    cout << "=== DEBUG ===" << endl;
    for (const auto& d : litCycles) cout << d << " ";
    cout << endl;

    for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < WIDTH; j++) {
            const size_t cycleNum = i * WIDTH + j + 1;
            if (litCycles.contains(cycleNum)) cout << "#";
            else cout << ".";
        }
        cout << endl;
    }
}
