package dag;
/*************************************************************************************************/
//BNP-ISH算法：                                                                                                                                                                                      
//1）定义任务图，本程序采用《并行分布计算中的调度算法理论与设计》P62图4.1的任务图。
//2）计算每一个节点t到最终节点的最长路经的b_level值，确定优先级，b_level值越大越优先
//3）从没有前向节点的节点开始执行，执行就是将节点i分配到n台机器中最优（可最早执行的）一台上，
//每一台机器的执行任务速度不一样，可设置。
//4）执行完成之后，再来执行。
//
//ISH算法：[ISH算法的时间复杂度是O(N*N)]
//1）计算每个节点的SBL属性；
//2）按SBL降序构造一个就绪任务表。
//开始时，就绪任务表仅包含入口节点，然后重复如下步骤直到所有节点都调度完毕：
//（1）用插入法将就绪表中的第一个节点调度到能够最早执行的处理机上；
//（2）如果调度这个节点产生一个空闲段，则在就绪表中寻找尽可能多的节点，这些节点可以调度到这些空闲的时间片，
//但不能更早调度到其他处理机上
//（3）把已经就绪的节点插入到就绪表中
//
//
//
//
/*************************************************************************************************/
#include "stdio.h"
int max(int SBL[10])//该函数实现按SBL属性值的降序顺序从任务列表中取任务
{
int maxsbl=0;
int maxid;
int i;
for(i=1;i<10;i++)
{
if(SBL[i]>maxsbl){maxsbl=SBL[i];maxid=i;}
}
return maxid;
}
int maxitem(int a[4])//返回长度为4的数组中最大的项的下标
{
int k=a[0]>a[1]?0:1;
k= a[k]>a[2]?k:2;
return a[k]>a[3]?k:3;
}
int minitem(int a[4])//返回长度为4的数组中最小项的下标【如果有最小项有相等的情况，则小下标优先】
{
int k=a[3]<a[2]?3:2;
k=a[k]<a[1]?k:1;
return a[k]<a[0]?k:0;
}
void main()
{
//定义任务图，采用二维数组表示所有节点间的关系，例如t1节点指向t2则node[1][2] = 4,而node[1][9] = 0，因为t1没有指向t9的路经
//由于没有节点t0则T[0][i]全部为0。
int i;
int j;
int node[10][10] = {0};
node[1][2] = 4; 
node[1][3] = 1;
node[1][4] = 1;
node[1][5] = 1;
node[1][7] = 10;
node[2][6] = 1;
node[2][7] = 1;
node[3][8] = 1;
node[4][8] = 1;
node[6][9] = 5;
node[7][9] = 6;
node[8][9] = 5;
/********************************************************************************/  
//定义每一个节点的计算时间,忽略t[0]
int t[10];
t[1] = 2;
t[2] = 3;
t[3] = 3;
t[4] = 4;
t[5] = 5;
t[6] = 4;
t[7] = 4;
t[8] = 4;
t[9] = 1;
/********************************************************************************/  
//定义每一个节点的前导节点,例如节点8的前导节点为节点3、4，则diaodao[8][3] = 1，diandao[8][4] = 1
int qiandao[10][10] = {0};
qiandao[2][1] = 1;
qiandao[3][1] = 1;
qiandao[4][1] = 1;
qiandao[5][1] = 1;
qiandao[7][1] = 1;
qiandao[6][2] = 1;
qiandao[7][2] = 1;
qiandao[8][3] = 1;
qiandao[8][4] = 1;
qiandao[9][6] = 1;
qiandao[9][7] = 1;
qiandao[9][8] = 1;

/********************************************************************************/  
//计算b_level值,忽略b_level[0],由于事先已知头节点和尾节点（在本例中头节点为t1，尾节点为t5和t9）
//所以从尾节点倒序计算每一个节点的b_level值。
int b_level[10] = {0};  
//尾节点b_level值为0
b_level[5] = 5;
b_level[9] = 1; 
//指示是否已经计算过b_level值
int b_cal[10] = {0};
b_cal[5] = 1;
b_cal[9] = 1;
int b_level_max, temp1, temp2, temp;
//从节点t1开始计算b_level值

//从尾节点9开始倒序计算b_level值，直到头节点1结束，由于最多有三次向前计算的机会，所以算三次就结束。
for(int i = 1; i < 10; i++) 
if(qiandao[9][i] == 1)  
b_level[i] = t[9] + t[i] + node[i][9];  
//计算倒数第二排节点
for(int i = 1; i < 9; i++)
{
if(b_level[i] != 0)
{
for(int j = 1; j < 10; j++)
if(qiandao[i][j] == 1)
{
temp1 = t[j] + node[j][i] + b_level[i];
if(temp1 > b_level[j])
b_level[j] = temp1;
}
}
}   
//最后计算t1节点 
for(int i = 1; i < 10; i++)
{
if(qiandao[i][1] == 1)
{
temp1 = t[1] + node[1][i] + b_level[i];
if(temp1 > b_level[1])
b_level[1] = temp1;
}

}

/********************************************************************************/  
/********************************************************************************/  
//计算b_level值,忽略b_level[0],由于事先已知头节点和尾节点（在本例中头节点为t1，尾节点为t5和t9）
//所以从尾节点倒序计算每一个节点的b_level值。
int SBL[10] = {0};  
//尾节点b_level值为0
SBL[5] = 5;
SBL[9] = 1; 
//指示是否已经计算过b_level值
int SBL_cal[10] = {0};
SBL_cal[5] = 1;
SBL_cal[9] = 1;
int SBL_max, SBL_temp1, SBL_temp2, SBL_temp;
//从节点t1开始计算b_level值

//从尾节点9开始倒序计算b_level值，直到头节点1结束，由于最多有三次向前计算的机会，所以算三次就结束。
for(int i = 1; i < 10; i++) 
if(qiandao[9][i] == 1)  
SBL[i] =t[9] + t[i] ;       
//计算倒数第二排节点
for(int i = 1; i < 9; i++)
{
if(SBL[i] != 0)
{
for(int j = 1; j < 10; j++)
if(qiandao[i][j] == 1)
{
SBL_temp1 = t[j]  + SBL[i];
if(SBL_temp1 > SBL[j])
SBL[j] = SBL_temp1;
}
}
}   
//最后计算t1节点 
for(int i = 1; i < 10; i++)
{
if(qiandao[i][1] == 1)
{
SBL_temp1 =t[1]  + SBL[i];
if(SBL_temp1 > SBL[1])
SBL[1] = SBL_temp1;
}

}

//计算每个节点的前向大数
int qian_max[10] = {0};
for(i = 1; i < 10; i++)
{
temp1 = 0;
for(int j = 0; j < 10; j++)
{
if(node[j][i] > qian_max[i])    
qian_max[i] = node[j][i];
}           

}
/////////////////////////////////////////////////////////////////////////////////////////////////////
//计算节点t1到其他所有节点的最大路径值
int t_level[10] = {0};
for(i = 1; i < 10; i++)
{
if(node[1][i] != 0)
{
t_level[i] = t[1] + node[1][i];             
if(t_level[i] < temp1)
t_level[i] = temp1;
for(int j = 1; j < 10; j++)
{
if(node[i][j] != 0) 
{
t_level[j] = t_level[i] + node[i][j]+t[i];              
for(int k = 1; k < 10; k++)
{
if(node[j][k] != 0) 
t_level[k] = t_level[j] + node[j][k];

}
}
}   
}
}
for(i = 1; i < 10; i++)
printf("t_level[%d] = %d\n", i, t_level[i]);


for(i = 1; i < 10; i++)
printf("sbl[%d] = %d\n", i, SBL[i]);
for(i = 1; i < 10; i++)
printf("qian_max[%d] = %d\n", i, qian_max[i]);
for(i = 1; i < 10; i++) 
{printf("b_level[%d]: %d\n ",i, b_level[i]);}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ISH
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

int pe[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//记录任务的开始时间，如pe[i]=x表示i任务开始执行时间为x
int time[4]={0,0,0,0};                     //记录机器当前时间，其值为当前分配的任务开始时间+任务的执行时间
int tdispatched[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//记录任务的分配情况，如tdispatched[i]=x表示i任务被分配到x号机器上
for(i=1;i<10;i++)  //从9个任务里面，按sbl的降序取值
{
int maxid=max(SBL);
SBL[maxid]=0;
//====================================分配maxid开始==================================================
if(maxid==1){	
pe[maxid]=0;
tdispatched[maxid]=0;
time[0]+=t[maxid];//0号机器被用掉的时间为该任务的运行耗时
}
else{
 int early[4]={-1,-1,-1,-1};//记录一个任务如果分配到0-3号机器能够最早执行的时间
 int a[4]={-1,-1,-1,-1};//记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）    
 for(int machid=0;machid<4;machid++)//往4台机器上分        //不考虑节点通信时间没必要循环4台机器  找开始时间最短机器
 {
 int j=1;
 a[0]=-1;
 a[1]=-1;
 a[2]=-1;
 a[3]=-1;
 //记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）
 //----------------------trymap----
 a[0]=time[machid];//记录当前机器的当前时间，如果任务被分配当前机器，任务必须在该时间以后执行
 for(int i=1;i<10;i++)//遍历 找到maxid节点的前驱
 {
  if(node[i][maxid]!=0)//找到一个前驱，node[i][maxid]的值为节点maxid与节点i的通信时间
  { if(tdispatched[i]==machid)//如果前驱节点i与该节点maxid都被分到了同一个机器
    {a[j]=time[machid];//maxid任务开始时间紧接着该机器的当前时间
    j++;}
    else{//如果前驱节点与该节点没有分配到同一个机器
    a[j]=pe[i]+t[i]+node[i][maxid];//则maxid任务开始时间为前驱任务节点完成时间+与之通信的时间
    j++;}
  }
 }
 //for完成后，j为 maxid 节点 前驱的个数，a[0....(j-1)]为对应前驱节点产生的开始时间，其中最大的为必须满足的
 early[machid]=a[maxitem(a)];
 //-----------------------------------------------------trymap---
 }   //得到early[0...3],里面最小的为选中的分配方案（开始时间最早）

int fangan=minitem(early);//记录应该分配到哪台机器（开始时间最早）
tdispatched[maxid]=fangan;//tdispatched[maxid]记录maxid任务被分配到哪台机器
pe[maxid]=early[fangan];  //pe[maxid]记录maxid任务的开始时间
time[fangan]=pe[maxid]+t[maxid];
}
//========================分配maxid结束
}
printf("【pe[i]表示i任务节点开始的执行时间】\n");
for(i = 1; i < 10; i++) 
{printf("pe[%d]: %d\n ",i, pe[i]);}
printf("【i任务节点被分配到编号为tdispatched[i]的机器】\n");
for(i = 1; i < 10; i++) 
{printf("tdispatched[%d]: %d\n ",i, tdispatched[i]);}
printf("【总共耗费的时间】");
{printf("%d\n ",time[maxitem(time)]);}


int   Start[9][4];
int   Flag[9];
int   jiuxu[9];
int   temp_z[9] = {0};
int   max;
int   start=0;
int   r[4];
int   SETC[9][4];
int    min;

for(int j=1;j<5;j++)
{r[j]=0;}
int k = 1;
while(k<10)
{
for(i=1;i<10;i++)
{
Flag[i]=1;
jiuxu[i]=0;
}

for(i=1;i<10;i++)
{   if(Flag[i]==1)
for(int j = 1; j < 5; j++)      
temp_z[i] += qiandao[i][j]; 



}
for(i=1;i<10;i++)
{
if(Flag[i]==1&&temp_z[i]==0)
jiuxu[i]=1;
}
for(i=1;i<10;i++)
{
if(jiuxu[i]==1)

max=b_level[i];
start=i;
break;      
}
for(i=1;i<10;i++)
{
if(jiuxu[i]==1&&b_level[i]>max)
max=b_level[i];
start=i;
}


int m=1;
min=r[1];
for(j=1;j<5;j++)
{      

if(r[j]<min)

m=j;

}

r[m]=r[m]+t[start];
if(t_level[start]>r[m])
r[m]=t_level[start]+t[start];

Flag[i]=0;

k++;
}
int rmax=0;
for(j=1;j<5;j++)
{
if(r[j]>rmax)
rmax=r[j];

}
for(j=1;j<5;j++)
{    printf("r[%d]： %d\n ",j,r[j]);}

printf("完成时间为： %d\n ",rmax);

}