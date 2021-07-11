



select 
avg(sal) 
from emp





select e.deptno,e.empno,e.sal
from emp e 



select e.deptno,count(e.empno),avg(e.sal)
from emp e 
group by e.deptno
having avg(e.sal) >(
select 
avg(sal) 
from emp);



select e.deptno,max(e.sal),avg(e.sal)
from emp e 
group by e.deptno;




select d.dname, avg(e.sal)
from dept d , emp e
where d.deptno = e.deptno
group by d.dname
having  avg(e.sal) =
(select max(avg(sal)) from emp group by deptno)
;


---聚合函数是针对一张表的数据来说的
select max(avg(sal)) from emp group by deptno;


select avg(sal) from emp group by deptno;






select d.deptno , d.dname,d.loc,temp.cnt,temp.avgs
from dept d,( select deptno ,count(empno) cnt,avg(sal) avgs from emp group by deptno) temp
where d.deptno = temp.deptno;






select e.empno,e.ename,e.sal,e.comm,e.job,e.hiredate,temp.mx,temp.mi
from emp e,(select deptno ,max(sal) mx,min(sal) mi from emp group by deptno) temp
 where e.deptno = (
select d.deptno
from dept d 
where d.dname ='SALES')
and temp.deptno = e.deptno;





select e.empno, e.ename, e.sal, e.job, e.hiredate,d.dname,d.loc,e2.ename,s.grade,temp.cnt,temp.avgyear
  from emp e, dept d , emp e2, salgrade s,(select deptno ,count(empno) cnt, round(avg(months_between(sysdate,hiredate)/12),2)  avgyear from emp group by deptno ) temp
 where e.sal > (select avg(sal) from emp)
 and d.deptno = e.deptno
 and e2.empno(+) = e.mgr
 and e.sal between s.losal and s.hisal
 and temp.deptno = e.deptno
 ;
 
 
 
 select e.empno, e.ename, e.sal, e.job, e.hiredate
   from emp e, dept d, emp e2
  where e.sal > (select avg(sal) from emp)
    and d.deptno = e.deptno
    and e2.empno(+) = e.mgr;
    
    
    
    select e.empno, e.ename, e.sal, d.dname, e2.ename,temp.cnt
      from emp e, dept d, emp e2,(select deptno ,count(empno) cnt from emp group by deptno) temp 
     where e.sal > any (select sal from emp where ename in ('ALLEN', 'CLARK'))
       and e.mgr =  e2.empno(+)
       and temp.deptno = e.deptno
       and e.deptno = d.deptno
       and e.ename  not in ('ALLEN', 'CLARK')
       ;




select e.empno, e.ename, e.sal
from emp e 
where e.sal > all (select sal from emp where ename in ('ALLEN', 'CLARK'));






select e.empno, e.ename, e.sal
from emp e 
where e.sal > any (select sal from emp where ename in ('ALLEN', 'CLARK')) and e.ename  not in ('ALLEN', 'CLARK');


select sal from emp where ename in ('ALLEN', 'CLARK');





select e.ename,e.sal,d.dname,temp.cnt,temp.avgsal
from emp e ,dept d ,(select deptno ,count(empno) cnt,round(avg(sal),2) avgsal from emp group by deptno) temp
where e.job ='MANAGER'
and d.deptno = e.deptno
and e.deptno = temp.deptno;