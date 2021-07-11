select *
  from emp t1
 where t1.job = (select job from emp e where e.ename = 'ALLEN')
   and t1.sal > (select e.sal from emp e where e.empno='7521')
;


select * 
from emp t
where (t.job,t.sal) =(
select e.job,e.sal
from emp e 
where e.ename='SCOTT');



select * from emp t
where (t.job,t.mgr) = (
select e.job,e.mgr
from emp e where e.empno = '7566');




select min(sal )
from emp group by deptno;





select min(sal),d.deptno
from emp e,dept d
where e.deptno(+) = d.deptno 
group by d.deptno

