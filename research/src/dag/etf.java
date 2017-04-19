package dag;

public class etf {
	/*************************************************************************************************/
	//ETF
	/*************************************************************************************************/
	#include "stdio.h"
	bool isallfinished(int *p)//判断是否所有节点都已调度完毕
	{
	for(int i=1;i<10;i++)
	{
	    if(p[i]!=1){return false;}
	}
	return true;
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
	    
	    
	int seq[10]={0,1,4,3,5,2,8,6,7,9};
	int pe[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//记录任务的开始时间，如pe[i]=x表示i任务开始执行时间为x
	int time[4]={0,0,0,0};                     //记录机器当前时间，其值为当前分配的任务开始时间+任务的执行时间
	int tdispatched[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//记录任务的分配情况，如tdispatched[i]=x表示i任务被分配到x号机器上
	    int isready[10]={0,1,1,1,1,1,1,1,1,1};//ready[i]=0表示i 任务未就绪，ready[i]=1表示任务i就绪(注1：该表的初值在下面的FOR中初始化；注2：所谓就绪指任务的父节点已执行完)
	    int isfinish[10]={0};//初始状态所有节点都未执行
	    
	    
	    
	while(!isallfinished(isfinish))//反复调度直到所有节点都执行完毕
	{
        int trystarttime[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	    int trymap[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};    
	    int early[4]={-1,-1,-1,-1};//记录一个任务如果分配到0-3号机器能够最早执行的时间
	    int selectedid;
	    for(int i=1;i<10;i++)//更新就绪列表
	    { bool flag=true;
	      for(int j=1;j<10;j++)
	      { if(node[j][i]!=0)
	          {if(isfinish[j]==0)//只要有一个父节点未执行完成，则该节点状态为未就绪
	            flag=false;
	          }
	       }
	       if(flag&&(isfinish[i]==0))//如果所有父节点都完成，并且该节点未被调度，则该节点属于就绪节点
	        isready[i]=1;
	       else isready[i]=0;
	    }	    
	for(int i=1;i<10;i++)  //遍历所有节点
	{
	if(isready[i]==1)//如果该节点属于就绪节点
	  selectedid=i;    //则对该节点进行考察
	else
	continue;//否则取下一个节点
	//=============分配selectedid开始==================
	if(selectedid==1){
	        	        
        trymap[selectedid]=0;
	        trystarttime[selectedid]=0;
    
	    }else{
   	    
	    int a[4]={-1,-1,-1,-1};//记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）    
	    for(int machid=0;machid<4;machid++)//往4台机器上分
	    {
	  int j=1;
	  a[0]=-1;
	  a[1]=-1;
	  a[2]=-1;
	  a[3]=-1;
	  
	  //记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）
	//--trymap----
	a[0]=time[machid];//记录当前机器的当前时间，如果任务被分配当前机器，任务必须在该时间以后执行
	for(int i=1;i<10;i++)//遍历 找到selectedid节点的前驱
	   {
	    if(node[i][selectedid]!=0)//找到一个前驱，node[i][selectedid]的值为节点selectedid与节点i的通信时间
	      {
	         
	          
	          if(tdispatched[i]==machid)//如果前驱节点i与该节点selectedid都被分到了同一个机器
	           {
	               
	               
	               a[j]=time[machid];//selectedid任务开始时间紧接着该机器的当前时间
	               
	               j++;
	            }else{//如果前驱节点与该节点没有分配到同一个机器
	               a[j]=pe[i]+t[i]+node[i][selectedid];//则selectedid任务开始时间为前驱任务节点完成时间+与之通信的时间
	              
	               j++;
	            }
	    
	      }
	   }//for完成后，j为 selectedid 节点 前驱的个数，a[0....(j-1)]为对应前驱节点产生的开始时间，其中最大的为必须满足的
	  
	early[machid]=a[maxitem(a)];
	//----------------------------------------------------------------------------------------------------------------trymap---
	    }   //得到early[0...3],里面最小的为选中的分配方案（开始时间最早）
	    
	    
	  
	   trymap[selectedid]=minitem(early);//使该节点（selectedid）有最早开始时间的机器
	   trystarttime[selectedid]=early[minitem(early)];//该节点（selectedid）的最早开始时间
	    
	    
	    }//(else)
	//==========================================================================================分配selectedid结束
	}//(for)就绪的节点都求到了最早开始时间，记录在trystarttime[]里，trymap[]则记录了分配方案,trydl[]里记录了每个就绪节点的最大DL
	int k1;
	//====================================================================================================取最早开始时间，如果时间一样 取小SBL
	for(int i=1;i<10;i++)
	    {
	    if(trystarttime[i]==-1) trystarttime[i]=1024;  //非就绪节点的开始时间设为无穷大(用1024代替)
	    
	    }
	    int k=trystarttime[9]<trystarttime[8]?9:8;
	    k=trystarttime[k]<trystarttime[7]?k:7;
	    k=trystarttime[k]<trystarttime[6]?k:6;
	    k=trystarttime[k]<trystarttime[5]?k:5;
	    k=trystarttime[k]<trystarttime[4]?k:4;
	    k=trystarttime[k]<trystarttime[3]?k:3;
	    k=trystarttime[k]<trystarttime[2]?k:2;
	    k=trystarttime[k]<trystarttime[1]?k:1;//取所有就绪节点中，具有最早开始时间的(存在这样的情况：几个节点同时具有最早开始时间)
	    for(int i=1;i<10;i++)//在几个节点同时具有最早开始时间的情况下，考察SBL，小SBL优先分配
	    {
	      if(trystarttime[k]==trystarttime[i])
	      {
	        if(SBL[k]<SBL[i])
	        {
	         k=i;
	        }
	      }
	    }
	k1=k;//k1的值为所有就绪节点中，选中准备分配的编号
	//====================================================================================================
	int fangan=trymap[k1];//记录应该分配到哪台机器（开始时间最早）
	    tdispatched[k1]=fangan;//tdispatched[selectedid]记录selectedid任务被分配到哪台机器
	    pe[k1]=trystarttime[k1];  //pe[selectedid]记录selectedid任务的开始时间
	    time[fangan]=pe[k1]+t[k1];//该机器（fangan）运行该任务（k1）后的时间
	    isfinish[k1]=1;//将该节点标记为已完成
	}//while(!isallfinished(isfinish))这个循环执行到所有任务节点都被完成
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	printf("\n【pe[i]表示i任务节点开始的执行时间】\n");
	for(i = 1; i < 10; i++) 
	    {printf("pe[%d]: %d\n ",i, pe[i]);}
	printf("【i任务节点被分配到编号为tdispatched[i]的机器】\n");
	for(i = 1; i < 10; i++) 
	    {printf("tdispatched[%d]: %d\n ",i, tdispatched[i]);}
	printf("【总共耗费的时间】");
	    {printf("%d\n ",time[maxitem(time)]);}
	}

}
