#include <stdio.h>
using namespace std;

int main(){
    const int n = 100; 
    int a[n];
    int giatrimin = -1; 
    int i = 0; 
    int vitri = -1; 
    for( i = 0; i < n; i++){
        if(a[i] > 0){
            if(a[i] < giatrimin || giatrimin == -1){
                giatrimin = a[i]; 
                vitri = i;

            }
        }
    }

   
}