#include <cstdio>
#include <vector>
#include <climits>

using namespace std;

long long decodeHuffman(const vector<long long>& a) {
    long long length = 0;
    int i = 0, j = 0;
    for (int k = 0; k < a.size() - 1; ++k) {
        long long a1 = (i + 1 < a.size()) ? a[i] : LLONG_MAX;
        long long a2 = (i + 2 < a.size()) ? a[i + 1] : LLONG_MAX;
        long long b1 = (j + 1 < k) ? a[j] : LLONG_MAX;
        long long b2 = (j + 2 < k) ? a[j + 1] : LLONG_MAX;

        if (a1 < b1) {
            length += a1;
            ++i;
            if (a2 < b1) {
                length += a2;
                ++i;
            }
            else {
                length += b1;
                ++j;
            }
        }
        else {
            length += b1;
            ++j;
            if (a1 < b2) {
                length += a1;
                ++i;
            }
            else {
                length += b2;
                ++j;
            }
        }
    }
    return length;
}

int main() {
    FILE* inFile = fopen("input.txt", "r");
    FILE* outFile = fopen("output.txt", "w");

    int n;
    fscanf(inFile, "%d", &n);

    vector<long long> frequencies(n);
    for (int i = 0; i < n; ++i) {
        fscanf(inFile, "%lld", &frequencies[i]);
    }

    long long result = decodeHuffman(frequencies);

    fprintf(outFile, "%lld", result);

    fclose(inFile);
    fclose(outFile);

    return 0;
}
