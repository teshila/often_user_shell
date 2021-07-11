
select e.empno,
       e.ename,
       to_char(e.hiredate, 'yyyy-mm-dd'),
       e.job,
       em.ename as manager,
       e.sal,
       (e.sal + nvl(e.comm, 0)) * 12 as incomme,
       s.grade,
       decode(s.grade,1,'E等工资',2,'D等工资',3,'C等工资',4，'B等工资',5,'A等工资'),
       d.deptno,
       d.dname,
       d.loc
  from emp e, emp em, dept d, salgrade s
 where e.mgr = em.empno(+)
   and e.deptno = d.deptno
   and e.sal between s.losal and s.hisal
and e.sal between 1500 and 3500
and to_char(e.hiredate,'yyyy')='1981'
order by incomme desc,e.job 
