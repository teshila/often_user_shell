
select e.empno,
       e.ename,
       to_char(e.hiredate, 'yyyy-mm-dd'),
       e.job,
       em.ename as manager,
       e.sal,
       (e.sal + nvl(e.comm, 0)) * 12 as incomme,
       s.grade,
       decode(s.grade,1,'E�ȹ���',2,'D�ȹ���',3,'C�ȹ���',4��'B�ȹ���',5,'A�ȹ���'),
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
