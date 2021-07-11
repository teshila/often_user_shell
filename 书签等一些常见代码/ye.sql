select t.doctitle,
       t.docfilename,
      t.docpuburl,
       t.docpubtime,
       t.docreltime,
       t.DOCFIRSTPUBTIME,
       t.crtime,
       t.opertime
  from wcmdocument t
 where t.docchannel = 83
   and t.docstatus = 10;
   
   
   
   select t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docpubtime,
       t.docreltime,
       t.DOCFIRSTPUBTIME,
       t.crtime,
       t.opertime
  from wcmdocument t
 where t.docchannel = 76
   and t.docstatus = 10;
   
   
   select t.doctitle,
       t.docfilename,
      t.docpuburl,
       t.docpubtime,
       t.docreltime,
       t.DOCFIRSTPUBTIME,
       t.crtime,
       t.opertime
  from wcmdocument t
 where t.docchannel = 77
   and t.docstatus = 10;
   


select t.doctitle,
       t.docpuburl,
       t.docfilename
      -- t.docpubtime,
     --  t.docreltime,
     --  t.DOCFIRSTPUBTIME,
     --  t.crtime,
     --  t.opertime
  from wcmdocument t
 where t.docchannel = 78
   and t.docstatus = 10 for update;
   
     select count(1)   from wcmdocument t where t.docchannel = 76   and t.docstatus = 10;--139  gzjb
     select count(1)   from wcmdocument t where t.docchannel = 77   and t.docstatus = 10;--248  xxyd
   select count(1)   from wcmdocument t where t.docchannel = 78   and t.docstatus = 10;--60  cybg
   
   
   drop table wcmdocument20171020_77;
   create table wcmdocument20171020_77_bak as select
       t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid      
  from wcmdocument t
 where t.docchannel = 77
   and t.docstatus = 10 ;
   
    create table wcmdocument20171020_76_bak as select
       t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid      
  from wcmdocument t
 where t.docchannel = 76
   and t.docstatus = 10 ;
   
   select * from wcmdocument20171020_77;
    select * from wcmdocument20171020_76;
   
 --substr(ename,2,length(ename)-1)
 
 
 --select  substr(t.docpuburl,2,length(t.docpuburl)-1)  from wcmdocument20171020_78 t;
 
 select  substr(t.docpuburl,instr(t.docpuburl,'/',-1)+1,length(t.docpuburl)) from wcmdocument20171020_76_bak t where t.docpuburl   not like '%.htm%';
 
  select  substr(t.docpuburl,instr(t.docpuburl,'/',-1)+1,length(t.docpuburl)) from wcmdocument20171020_76 t;
  
update wcmdocument b set b.docfilename  =(select  substr(t.docpuburl,instr(t.docpuburl,'/',-1)+1,length(t.docpuburl)) from wcmdocument20171020_76_bak t where b.docid = t.docid   and t.docpuburl   not like '%.htm%')
 where b.docchannel = 76  and b.docstatus = 10
 
 select  t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid   from wcmdocument20171020_76 t where t.docchannel = 76  and t.docstatus = 10;
 

  select count(1)   from wcmdocument_111111111 t where t.docchannel = 77   and t.docstatus = 10;--248  xxyd


 select
       t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid  
  from wcmdocument_20170913 t
 where t.docchannel = 76
   and t.docstatus = 10  and 
   --t.doctitle = '质量强市简报第82期'
  t.docfilename is null;
  
  
 select
       t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid  
  from wcmdocument t
 where t.docchannel = 76
   and t.docstatus = 10  and 
   --t.doctitle = '质量强市简报第82期'
  t.docfilename is null;

select * from wcmdocument20171020_78_bak;


select t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid    from wcmdocument_20170913 t  where t.docchannel = 76  and t.docstatus = 10 ;
       
       select   t.doctitle,
       t.docpuburl,
       t.docfilename,
       t.docid   from wcmdocument  t where t.doctitle = '深圳市“两建”工作简报【2017】总第108期';
       
       
       
       select * from wcmdocument t where t.doctitle  like '坪山局召开坪山区电梯应急预案编制宣贯会' 
