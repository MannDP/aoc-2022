#include "../competitive.hpp"

// there is no pipelining
int main(const int argc, const char* const argv[]) {
    int V;
    int X = 1;
    int cycle = 1; // which cycle is running?
    unordered_set<int> sampleTimes{20, 60, 100, 140, 180, 220};
    int signal = 0;

    string cmd;
    while (cin >> cmd) {
        if (cmd == "noop") {
            if (sampleTimes.contains(cycle)) {
                signal += X * cycle;
            }
            cycle += 1;
        } else {
            if (sampleTimes.contains(cycle)) signal += X * cycle;
            else if (sampleTimes.contains(cycle + 1)) signal += X * (cycle + 1);
            cin >> V;
            X += V;
            cycle += 2;
        }
    }

    cout << signal << endl;
}
