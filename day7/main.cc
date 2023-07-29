#include "../competitive.hpp"

struct File {
    string name;
    bool isDir;
    int size;
    unordered_map<string, File*> files;

    File(string name, bool isDir, int size): name{std::move(name)}, isDir{isDir}, size{size} {}
    ~File();

    int calcSize(int& aggr);
};

File::~File() {
    for (auto& [_, file]: files) {
        delete file;
    }
}

int File::calcSize(int& aggr) {
    if (isDir) {
        for (const auto& [_, file] : files) {
        size += file->calcSize(aggr);
        }
        if (size <= 100000) aggr += size;
    }
    return size; 
}

int main() {
    auto root = new File{"/", true, 0};
    vector<File*> traversal = {root};

    string line;
    getline(cin, line); // discard "cd /"

    while(getline(cin, line)) {
        istringstream ss{line};
        if (line[0] == '$') {
            if (line == "$ ls") continue;

            string targetDir;
            ss >> targetDir >> targetDir >> targetDir; // '$ cd'
            if (targetDir == "..") {
                traversal.pop_back();
            } else {
                traversal.push_back(traversal.back()->files[targetDir]);
            }
        } else {
            // assume we are finding the results of "ls"
            int size;
            string name;
            if (isnumber(ss.peek())) {
                ss >> size;
                ss >> name;
                traversal.back()->files[name] = new File(name, false, size);
            } else {
                ss >> name >> name;
                assert(name != "dir");
                traversal.back()->files[name] = new File(name, true, 0);
            }
        }
    }

    int aggr = 0;
    root->calcSize(aggr);
    cout << aggr << endl;    

    delete root;
}
