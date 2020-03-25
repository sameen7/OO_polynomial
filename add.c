#include<stdio.h>
int main(){
    int num1[100], pow1[100], num2[100], pow2[100];
    char s[100], op;
    int i=0,j=0,m=0,n,l=0,k=0,flag,t1,t2,pos;
    gets(s);
    while(s[k]!='}'){
        if(s[k]==' '){
            continue;
        }else if(s[k]=='('){
            k++;
            if(s[k]!='-'){
                for(n=0;s[k]>='0'&&s[k]<='9';k++){
                    n=10*n+s[k]-'0';
                }
                num1[i++]=n;
                k--;
            }else{
                k++;
                for(n=0;s[k]>='0'&&s[k]<='9';k++){
                    n=10*n+s[k]-'0';
                }
                num1[i++]=0-n;
                k--;
            }
        }else if(s[k]==','){
            k++;
            if(s[k]==' '){
                continue;
            }else if(s[k]=='('){
                k--;
            }else{
                if(s[k]!='-'){
                   for(n=0;s[k]>='0'&&s[k]<='9';k++){
                        n=10*n+s[k]-'0';
                   }
                   pow1[j++]=n;
                   k--;
                }else{
                    k++;
                    for(n=0;s[k]>='0'&&s[k]<='9';k++){
                        n=10*n+s[k]-'0';
                   }
                   pow1[j++]=0-n;
                   k--;
                }
            }
        }
        k++;
    }
    k++;
    while(s[k]==' '){
        k++;
    }
    op=s[k];
    for(k++;s[k]!='\0';k++){
        if(s[k]==' '){
            continue;
        }else if(s[k]=='('){
            k++;
            if(s[k]!='-'){
                for(n=0;s[k]>='0'&&s[k]<='9';k++){
                    n=10*n+s[k]-'0';
                }
                num2[m++]=n;
                k--;
            }else{
                k++;
                for(n=0;s[k]>='0'&&s[k]<='9';k++){
                    n=10*n+s[k]-'0';
                }
                num2[m++]=0-n;
                k--;
            }
        }else if(s[k]==','){
            k++;
            if(s[k]==' '){
                continue;
            }else if(s[k]=='('){
                k--;
            }else{
                if(s[k]!='-'){
                   for(n=0;s[k]>='0'&&s[k]<='9';k++){
                        n=10*n+s[k]-'0';
                   }
                   pow2[l++]=n;
                   k--;
                }else{
                    k++;
                    for(n=0;s[k]>='0'&&s[k]<='9';k++){
                        n=10*n+s[k]-'0';
                   }
                   pow2[l++]=0-n;
                   k--;
                }
            }
        }else if(s[k]=='}'){
            for(l=0;l<m;l++){
                    flag=0;
                for(j=0;j<i;j++){
                    if(pow1[j]==pow2[l]){
                        if(op=='+'){
                            num1[j]+=num2[l];
                        }else{
                            num1[j]-=num2[l];
                        }
                        flag=1;
                    }
                }
                if(flag==0){
                    if(op=='+'){
                        num1[i++]=num2[l];
                        pow1[i-1]=pow2[l];
                    }else{
                        num1[i++]=0-num2[l];
                        pow1[i-1]=pow2[l];
                    }
                }
            }
            k++;
            while(s[k]==' '){
                k++;
            }
            op=s[k];
            m=0;
            l=0;
        }
    }
    m=i;
    for(j=0;j<m;j++){
        pos=j;
        for(l=j+1;l<m;l++){
            if(pow1[l]<pow1[pos]){
                pos=l;
            }
        }
        if(pos!=j){
            t1=pow1[pos];
            pow1[pos]=pow1[j];
            pow1[j]=t1;
            t2=num1[pos];
            num1[pos]=num1[j];
            num1[j]=t2;
        }
    }
    for(j=0;j<i;j++){
        printf("%d %d ",num1[j],pow1[j]);
    }
    return 0;
}
