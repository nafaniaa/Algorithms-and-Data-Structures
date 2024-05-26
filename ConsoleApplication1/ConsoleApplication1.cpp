#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    int n;
    cout << "Введите количество точек: ";
    cin >> n;

    vector<int> x(n), y(n);
    cout << "Введите координаты точек (x y):" << endl;
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }

    vector<int> ans;
    ans.push_back(0);
    vector<bool> used(n, false);
    used[0] = true;
    int cur = 0;
    long long pans = 0;

    while (ans.size() != n) {
        int ncur = -1;
        for (int i = 0; i < n; i++) {
            if (!used[i] && (ncur == -1 || (abs(x[i] - x[cur]) + abs(y[i] - y[cur]) < abs(x[ncur] - x[cur]) + abs(y[ncur] - y[cur])))) {
                ncur = i;
            }
        }
        used[ncur] = true;
        ans.push_back(ncur);
        pans += abs(x[ncur] - x[cur]) + abs(y[ncur] - y[cur]);
        cur = ncur;
    }

    pans += abs(x[ans[0]] - x[cur]) + abs(y[ans[0]] - y[cur]);

    cout << "Порядок посещения точек: ";
    for (int i = 0; i < n; i++) {
        cout << ans[i] + 1 << " ";
    }
    cout << ans[0] + 1 << endl;

    cout << "Суммарное расстояние: " << pans << endl;

    return 0;
}
