#include <iostream>
#include <fstream>
#include <string>
#include <vector>
using namespace std;
#define maxsize 128
static bool map[maxsize][maxsize];
static bool visited[maxsize][maxsize];
int getmin(vector<int> temp[]);
vector<int> traversal(int x, int y, int row, int col);

int main(int argc, char** args)
{
     if(argc != 2) {
         cout << "incorrect !" << endl;
         return -1;
     }

    ifstream fin;
    fin.open(args[1]);
    ofstream out(args[2]);
    int row=0, col;
    string line;
    while (getline(fin, line))
    {
        col = 0;
        for(int i=0; i<line.length(); i++)
        {
            if(line[i] == '1' || line[i] == '0' )
                line[i] == '1' ? map[row][col++] = true : map[row][col++] = false;
            else
                continue;
        }
        row++;
    }

    vector<int> res = traversal(row-1, 0, row, col);
    for(int i=0; i<res.size(); i++)
    {
        if(i == 0)
            out << res[res.size()-i-1];
        else
            out << "-" << res[res.size()-i-1];
    }
}

int getmin(vector<int> temp[])
{
    int min = 1000000;
    int index = 0;
    for(int i=0; i<4 ; i++)
    {
        if(temp[i].size() == 0)
            continue;

        if(min > temp[i].size())
        {
            min = temp[i].size();
            index = i;
        }
    }
    return index;
}

vector<int> traversal(int x, int y, int row, int col)
{
    if(x >= row || y >= col || x <0 || y < 0 || map[x][y] == false ||visited[x][y] == true)
        return vector<int>();

    if(x == 0 && y == col-1){
        vector<int> temp;
        temp.push_back(col);
        return temp;
    }
    visited[x][y] = true;
    vector<int> temp[4];
    temp[0] = traversal(x+1,y,row,col);
    temp[1] = traversal(x-1,y,row,col);
    temp[2] = traversal(x,y+1,row,col);
    temp[3] = traversal(x,y-1,row,col);
    int result = getmin(temp);
    visited[x][y] = false;

    if(temp[result].size() == 0)
        return temp[0];
    else{
        temp[result].push_back(x*row + y + 1);
        return  temp[result];
    }

}
