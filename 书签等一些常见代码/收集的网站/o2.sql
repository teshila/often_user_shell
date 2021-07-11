select d.deptno,d.dname,d.loc,temp.count,temp.avg
from dept  d,(
select e.deptno ,count(e.empno) count,round(avg(e.sal),2) avg
from emp e
group by e.deptno) temp
where d.deptno = temp.deptno(+);







select e.empno,e.ename,e.sal,e.comm,e.job,to_char(e.hiredate,'yyyy-mm-dd'),temp.min,temp.max
from emp e ,(
select t.deptno,min(t.sal) min,max(t.sal) max
from emp t
group by t.deptno) temp ,

(select d.deptno,d.dname
from dept d) temp2

where temp2.dname='SALES' 
and temp2.deptno = e.deptno
and e.deptno = temp.deptno;





select e.empno,e.ename,e.sal,e.comm,e.job,to_char(e.hiredate,'yyyy-mm-dd'),temp.max,temp.min
from emp e,(select t1.deptno,max(t1.sal) max,min(t1.sal) min  from emp t1 group by t1.deptno) temp
where e.deptno = (
select d.deptno
from dept d 
where d.dname='SALES')
and temp.deptno = e.deptno;





select  count(t3.empno),avg(t3.sal),avg(months_between(sysdate,t3.hiredate))
from emp t3







select t1.empno,t1.ename,t1.sal,t1.job,t1.hiredate,d.dname,d.loc,t2.ename
from emp t1 , dept d ,emp t2,salgrade s,



where t1.sal>(
select avg(e.sal)
from emp e)
and t1.deptno = d.deptno
and t1.mgr = t2.empno
