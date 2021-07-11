select * from 
product ;



select * from product p 
where p.product_id in (
select t.product_id  from shopproduct t where 
t.shop_id ='000C');






select * from product p 
where  exists  (
select t.product_id  from shopproduct t where 
t.shop_id ='000C' and p.product_id = t.product_id);




select cast('2021' as integer) from dual;


select cast(to_date('2020-12-14','yyyy-mm-dd') as DATE) from dual;


SELECT CAST('2009-12-14' AS DATE) AS date_col
  FROM DUAL;
  
  
  case分为简单表达式和case 搜索表达式，比如case  when  ... then  
  cast是转换函数。如case('123 ' as integer);
  
  
  select * from shopproduct;



select * from product t 
where exists 
(select 0 from shopproduct t2 where t2.product_id = t.product_id
and  t2.shop_id='000C');




select 
      case when t.product_type ='厨房用具' then 'A:'||t.product_type
           when t.product_type ='办公用品' then 'B:'||t.product_type
           when t.product_type ='衣服' then 'C:'||t.product_type 
      end
         
from product t;





select case when t2.product_type='厨房用具' then 'A1'|| t2.product_type
            when t2.product_type = '办公用品' then 'B2'
            else null
       end 
from product t2;


select t1.product_name,t1.product_id
from product t1
union 
select t2.product_name ,t2.product_id
from  product2 t2;




select cast('2020/01/01' as date) from dual;




with e as 
(select * from dual) 



/*深圳，厦门，汕头，珠海*/



窗口函数  over ([partion by 列清单]  order by 列清单);




select t.product_type,t.regist_date,sum(t.sale_price)
from product t 
group by rollup(t.product_type,t.regist_date);




with temp as 
(select * from product )
select  *
 from product t2, temp
where t2.product_id = temp.product_id;




select * from emp e 
where e.ename = upper('smith');




select *
from emp  e
where length(e.ename ) = 5;




select substr(ename,0,2)
from emp;



select substr(ename,3)
from emp e
where e.deptno ='10'


select *
from emp e 
where substr(e.ename,0,3 ) ='JAM';

select * from emp e 
where trim(e.ename) = 'SMITH' ;


select lpad('ye',20,'**') from dual;


select instr('mldn java','java') from dual;






























select round(23.6) from dual;

select trunc(768.69) from dual;
select trunc(768.69,-1) from dual;
select trunc(768.69,-2) from dual;



select sysdate from dual;
select add_months(sysdate,-6) from dual;-----解决存在的闰年问题


select * from emp e
where e.hiredate = last_day(e.hiredate) - 2;




select extract(month from date '2020-01-02') from dual;


select to_timestamp('2020-01-02 22:20:30','yyyy-mm-dd hh24:mi:ss') from dual;


select to_date('2021-06-10','yyyy-mm-dd') from dual;


select nvl(e.comm,2) from emp e;




select months_between(sysdate,to_date('2021-09-10','yyyy-mm-dd')) from dual;




select e.empno,e.ename,e.job,e.sal,d.dname,d.loc
from emp e inner join dept d
on e.deptno = d.deptno
;




select * from salgrade;

select e.empno,e.ename,e.hiredate,e.sal,s.grade
from emp e, salgrade s
where e.sal between s.losal and s.hisal;




select e.empno,e.ename,e.hiredate,e.sal,s.grade
from emp e, salgrade s 
where e.sal >= s.losal and e.sal<= s.hisal;




select e.empno,e.ename,e.sal,decode(s.grade,1,'E等',2,'D等',3,'C等',4,'B等',5,'A等')
from emp e,salgrade s
where e.sal between s.losal and s.hisal;




select e.empno,
       e.ename,
       e.sal,
       d.dname,
      
       decode(s.grade, 1, 'E等', 2, 'D等', 3, 'C等', 4, 'B等', 5, 'A等')
  from emp e, salgrade s，dept d
 where e.sal between s.losal and s.hisal
   and e.deptno = d.deptno;
   
   
   
   
   select * from dept d;



select mod(months_between(sysdate,e.hiredate),12)
from emp e 




select extract(year from date '2021-06-26') from dual;


select extract(year from sysdate) from dual;

---错误
select extract(year from timestamp) from dual;


select extract(year from systimestamp) from dual;


select systimestamp from dual;  


select cast(sysdate as timestamp) date_to_timestamp  
from dual;  
