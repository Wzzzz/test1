package dag;

public class etf {
	/*************************************************************************************************/
	//ETF
	/*************************************************************************************************/
	#include "stdio.h"
	bool isallfinished(int *p)//�ж��Ƿ����нڵ㶼�ѵ������
	{
	for(int i=1;i<10;i++)
	{
	    if(p[i]!=1){return false;}
	}
	return true;
	}
	int maxitem(int a[4])//���س���Ϊ4����������������±�
	{
	    int k=a[0]>a[1]?0:1;
	    k= a[k]>a[2]?k:2;
	    return a[k]>a[3]?k:3;
	}
	int minitem(int a[4])//���س���Ϊ4����������С����±꡾�������С������ȵ��������С�±����ȡ�
	{
	    int k=a[3]<a[2]?3:2;
	    k=a[k]<a[1]?k:1;
	    return a[k]<a[0]?k:0;
	}
	void main()
	{
	    //��������ͼ�����ö�ά�����ʾ���нڵ��Ĺ�ϵ������t1�ڵ�ָ��t2��node[1][2] = 4,��node[1][9] = 0����Ϊt1û��ָ��t9��·��
	    //����û�нڵ�t0��T[0][i]ȫ��Ϊ0��
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
	    //����ÿһ���ڵ�ļ���ʱ��,����t[0]
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
	    //����ÿһ���ڵ��ǰ���ڵ�,����ڵ�8��ǰ���ڵ�Ϊ�ڵ�3��4����diaodao[8][3] = 1��diandao[8][4] = 1
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
	    //����b_levelֵ,����b_level[0],����������֪ͷ�ڵ��β�ڵ㣨�ڱ�����ͷ�ڵ�Ϊt1��β�ڵ�Ϊt5��t9��
	    //���Դ�β�ڵ㵹�����ÿһ���ڵ��b_levelֵ��
	    int b_level[10] = {0};  
	    //β�ڵ�b_levelֵΪ0
	    b_level[5] = 5;
	    b_level[9] = 1; 
	    //ָʾ�Ƿ��Ѿ������b_levelֵ
	    int b_cal[10] = {0};
	    b_cal[5] = 1;
	    b_cal[9] = 1;
	    int b_level_max, temp1, temp2, temp;
	    //�ӽڵ�t1��ʼ����b_levelֵ
	    
	    //��β�ڵ�9��ʼ�������b_levelֵ��ֱ��ͷ�ڵ�1���������������������ǰ����Ļ��ᣬ���������ξͽ�����
	    for(int i = 1; i < 10; i++) 
	        if(qiandao[9][i] == 1)  
	            b_level[i] = t[9] + t[i] + node[i][9];  
	    //���㵹���ڶ��Žڵ�
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
	    //������t1�ڵ� 
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
	    //����b_levelֵ,����b_level[0],����������֪ͷ�ڵ��β�ڵ㣨�ڱ�����ͷ�ڵ�Ϊt1��β�ڵ�Ϊt5��t9��
	    //���Դ�β�ڵ㵹�����ÿһ���ڵ��b_levelֵ��
	    int SBL[10] = {0};  
	    //β�ڵ�b_levelֵΪ0
	    SBL[5] = 5;
	    SBL[9] = 1; 
	    //ָʾ�Ƿ��Ѿ������b_levelֵ
	    int SBL_cal[10] = {0};
	    SBL_cal[5] = 1;
	    SBL_cal[9] = 1;
	    int SBL_max, SBL_temp1, SBL_temp2, SBL_temp;
	    //�ӽڵ�t1��ʼ����b_levelֵ
	    
	    //��β�ڵ�9��ʼ�������b_levelֵ��ֱ��ͷ�ڵ�1���������������������ǰ����Ļ��ᣬ���������ξͽ�����
	    for(int i = 1; i < 10; i++) 
	        if(qiandao[9][i] == 1)  
	            SBL[i] =t[9] + t[i] ;       
	    //���㵹���ڶ��Žڵ�
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
	    //������t1�ڵ� 
	    for(int i = 1; i < 10; i++)
	    {
	        if(qiandao[i][1] == 1)
	        {
	            SBL_temp1 =t[1]  + SBL[i];
	            if(SBL_temp1 > SBL[1])
	                SBL[1] = SBL_temp1;
	        }
	        
	    }
	    
	//����ÿ���ڵ��ǰ�����
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
	//����ڵ�t1���������нڵ�����·��ֵ
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
	int pe[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//��¼����Ŀ�ʼʱ�䣬��pe[i]=x��ʾi����ʼִ��ʱ��Ϊx
	int time[4]={0,0,0,0};                     //��¼������ǰʱ�䣬��ֵΪ��ǰ���������ʼʱ��+�����ִ��ʱ��
	int tdispatched[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};//��¼����ķ����������tdispatched[i]=x��ʾi���񱻷��䵽x�Ż�����
	    int isready[10]={0,1,1,1,1,1,1,1,1,1};//ready[i]=0��ʾi ����δ������ready[i]=1��ʾ����i����(ע1���ñ�ĳ�ֵ�������FOR�г�ʼ����ע2����ν����ָ����ĸ��ڵ���ִ����)
	    int isfinish[10]={0};//��ʼ״̬���нڵ㶼δִ��
	    
	    
	    
	while(!isallfinished(isfinish))//��������ֱ�����нڵ㶼ִ�����
	{
        int trystarttime[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	    int trymap[10]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};    
	    int early[4]={-1,-1,-1,-1};//��¼һ������������䵽0-3�Ż����ܹ�����ִ�е�ʱ��
	    int selectedid;
	    for(int i=1;i<10;i++)//���¾����б�
	    { bool flag=true;
	      for(int j=1;j<10;j++)
	      { if(node[j][i]!=0)
	          {if(isfinish[j]==0)//ֻҪ��һ�����ڵ�δִ����ɣ���ýڵ�״̬Ϊδ����
	            flag=false;
	          }
	       }
	       if(flag&&(isfinish[i]==0))//������и��ڵ㶼��ɣ����Ҹýڵ�δ�����ȣ���ýڵ����ھ����ڵ�
	        isready[i]=1;
	       else isready[i]=0;
	    }	    
	for(int i=1;i<10;i++)  //�������нڵ�
	{
	if(isready[i]==1)//����ýڵ����ھ����ڵ�
	  selectedid=i;    //��Ըýڵ���п���
	else
	continue;//����ȡ��һ���ڵ�
	//=============����selectedid��ʼ==================
	if(selectedid==1){
	        	        
        trymap[selectedid]=0;
	        trystarttime[selectedid]=0;
    
	    }else{
   	    
	    int a[4]={-1,-1,-1,-1};//��¼�ɸ������ڵ�����Ŀ�ʼʱ�����ƣ�����ͬ������ͨ��ʱ��������ͬ������ͨ��ʱ�������    
	    for(int machid=0;machid<4;machid++)//��4̨�����Ϸ�
	    {
	  int j=1;
	  a[0]=-1;
	  a[1]=-1;
	  a[2]=-1;
	  a[3]=-1;
	  
	  //��¼�ɸ������ڵ�����Ŀ�ʼʱ�����ƣ�����ͬ������ͨ��ʱ��������ͬ������ͨ��ʱ�������
	//--trymap----
	a[0]=time[machid];//��¼��ǰ�����ĵ�ǰʱ�䣬������񱻷��䵱ǰ��������������ڸ�ʱ���Ժ�ִ��
	for(int i=1;i<10;i++)//���� �ҵ�selectedid�ڵ��ǰ��
	   {
	    if(node[i][selectedid]!=0)//�ҵ�һ��ǰ����node[i][selectedid]��ֵΪ�ڵ�selectedid��ڵ�i��ͨ��ʱ��
	      {
	         
	          
	          if(tdispatched[i]==machid)//���ǰ���ڵ�i��ýڵ�selectedid�����ֵ���ͬһ������
	           {
	               
	               
	               a[j]=time[machid];//selectedid����ʼʱ������Ÿû����ĵ�ǰʱ��
	               
	               j++;
	            }else{//���ǰ���ڵ���ýڵ�û�з��䵽ͬһ������
	               a[j]=pe[i]+t[i]+node[i][selectedid];//��selectedid����ʼʱ��Ϊǰ������ڵ����ʱ��+��֮ͨ�ŵ�ʱ��
	              
	               j++;
	            }
	    
	      }
	   }//for��ɺ�jΪ selectedid �ڵ� ǰ���ĸ�����a[0....(j-1)]Ϊ��Ӧǰ���ڵ�����Ŀ�ʼʱ�䣬��������Ϊ���������
	  
	early[machid]=a[maxitem(a)];
	//----------------------------------------------------------------------------------------------------------------trymap---
	    }   //�õ�early[0...3],������С��Ϊѡ�еķ��䷽������ʼʱ�����磩
	    
	    
	  
	   trymap[selectedid]=minitem(early);//ʹ�ýڵ㣨selectedid�������翪ʼʱ��Ļ���
	   trystarttime[selectedid]=early[minitem(early)];//�ýڵ㣨selectedid�������翪ʼʱ��
	    
	    
	    }//(else)
	//==========================================================================================����selectedid����
	}//(for)�����Ľڵ㶼�������翪ʼʱ�䣬��¼��trystarttime[]�trymap[]���¼�˷��䷽��,trydl[]���¼��ÿ�������ڵ�����DL
	int k1;
	//====================================================================================================ȡ���翪ʼʱ�䣬���ʱ��һ�� ȡСSBL
	for(int i=1;i<10;i++)
	    {
	    if(trystarttime[i]==-1) trystarttime[i]=1024;  //�Ǿ����ڵ�Ŀ�ʼʱ����Ϊ�����(��1024����)
	    
	    }
	    int k=trystarttime[9]<trystarttime[8]?9:8;
	    k=trystarttime[k]<trystarttime[7]?k:7;
	    k=trystarttime[k]<trystarttime[6]?k:6;
	    k=trystarttime[k]<trystarttime[5]?k:5;
	    k=trystarttime[k]<trystarttime[4]?k:4;
	    k=trystarttime[k]<trystarttime[3]?k:3;
	    k=trystarttime[k]<trystarttime[2]?k:2;
	    k=trystarttime[k]<trystarttime[1]?k:1;//ȡ���о����ڵ��У��������翪ʼʱ���(��������������������ڵ�ͬʱ�������翪ʼʱ��)
	    for(int i=1;i<10;i++)//�ڼ����ڵ�ͬʱ�������翪ʼʱ�������£�����SBL��СSBL���ȷ���
	    {
	      if(trystarttime[k]==trystarttime[i])
	      {
	        if(SBL[k]<SBL[i])
	        {
	         k=i;
	        }
	      }
	    }
	k1=k;//k1��ֵΪ���о����ڵ��У�ѡ��׼������ı��
	//====================================================================================================
	int fangan=trymap[k1];//��¼Ӧ�÷��䵽��̨��������ʼʱ�����磩
	    tdispatched[k1]=fangan;//tdispatched[selectedid]��¼selectedid���񱻷��䵽��̨����
	    pe[k1]=trystarttime[k1];  //pe[selectedid]��¼selectedid����Ŀ�ʼʱ��
	    time[fangan]=pe[k1]+t[k1];//�û�����fangan�����и�����k1�����ʱ��
	    isfinish[k1]=1;//���ýڵ���Ϊ�����
	}//while(!isallfinished(isfinish))���ѭ��ִ�е���������ڵ㶼�����
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	printf("\n��pe[i]��ʾi����ڵ㿪ʼ��ִ��ʱ�䡿\n");
	for(i = 1; i < 10; i++) 
	    {printf("pe[%d]: %d\n ",i, pe[i]);}
	printf("��i����ڵ㱻���䵽���Ϊtdispatched[i]�Ļ�����\n");
	for(i = 1; i < 10; i++) 
	    {printf("tdispatched[%d]: %d\n ",i, tdispatched[i]);}
	printf("���ܹ��ķѵ�ʱ�䡿");
	    {printf("%d\n ",time[maxitem(time)]);}
	}

}
