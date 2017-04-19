package dag;

import java.util.*;


public class test1 {
	public static int max(int a[])    //找一个数组最大值的索引
	{
     int m = 0;
	 for(int i = 0;i<a.length;i++) 
	 {
	   if(a[i]>=a[0])
	     m = i;
	 }	
     return m;
	}
	public static int min(int a[])    //找一个数组最大值的索引
	{
     int m = 0;
	 for(int i = 0;i<a.length;i++) 
	 {
	   if(a[i]<=a[0])
	     m = i;
	 }	
     return m;
	}
		
    public static void main(String[] args){
    int pe[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};	         //表示任务开始时间
    int time[] = {0,0,0};                                //机器开始时间
    int dispatched[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};	 //任务分配给资源情况1-3
    int b_level[] = {0,1,1,2,3,3,3,4,4,4};  //10个任务的b_level
    int t[] = {2,3,3,4,2,3,3,3,1,4};
    int qiandao[][] = new int[10][10];  //a[2][1]=1表示任务2在任务1之前执行
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
    for(int i = 0;i<10;i++)     
    {
     int maxid = max(b_level);    //选最大的b_level值 
     b_level[maxid] = 0;          
     if(maxid==1){
       pe[maxid] = 0;
       dispatched[maxid]=0;	 
       time[0]+=t[maxid];	 
     }
     else
     {
        int early[4]={-1,-1,-1,-1};//记录一个任务如果分配到0-3号机器能够最早执行的时间
        int a[4]={-1,-1,-1,-1};//记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）    
        for(int machid=0;machid<4;machid++)//往4台机器上分
    	{
    	int j=1;
    	a[0]=-1;
    	a[1]=-1;
        a[2]=-1;
        a[3]=-1;
        //记录由各个父节点产生的开始时间限制（包括同机器无通信时间情况与非同机器有通信时间情况）
        //----------------------------------------------------trymap---------------------------------
        a[0]=time[machid];//记录当前机器的当前时间，如果任务被分配当前机器，任务必须在该时间以后执行
        for(int i=1;i<10;i++)//遍历 找到maxid节点的前驱
    	{
    	  if(node[i][maxid]!=0)//找到一个前驱，node[i][maxid]的值为节点maxid与节点i的通信时间
    	  {
    	  if(tdispatched[i]==machid)//如果前驱节点i与该节点maxid都被分到了同一个机器
    	  {
    	   a[j]=time[machid];//maxid任务开始时间紧接着该机器的当前时间
    	   j++;
    	  }else
    	  {                                   //如果前驱节点与该节点没有分配到同一个机器
    	   a[j]=pe[i]+t[i]+node[i][maxid];//则maxid任务开始时间为前驱任务节点完成时间+与之通信的时间
    	   j++;
    	  }
    		 }
    		 }//for完成后，j为 maxid 节点 前驱的个数，a[0....(j-1)]为对应前驱节点产生的开始时间，其中最大的为必须满足的
    		 early[machid]=a[max(a)];
    		 }   //得到early[0...3],里面最小的为选中的分配方案（开始时间最早）
    		 int fangan=min(early);//记录应该分配到哪台机器（开始时间最早）
    		 dispatched[maxid]=fangan;//tdispatched[maxid]记录maxid任务被分配到哪台机器
    		 pe[maxid]=early[fangan];  //pe[maxid]记录maxid任务的开始时间
    		 time[fangan]=pe[maxid]+t[maxid];
        }
    }
    } 
}

