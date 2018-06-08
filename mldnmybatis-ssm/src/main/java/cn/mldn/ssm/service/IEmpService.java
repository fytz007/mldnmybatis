package cn.mldn.ssm.service;

import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import cn.mldn.ssm.vo.Emp;

@CacheConfig(cacheNames = "emp")
public interface IEmpService {
	public boolean add(Emp vo) ;

	@CacheEvict(key="#ids[0]")
	public boolean delete(Set<Long> ids) ; 
	
	@CachePut(key = "#vo.empno", unless = "#result == null")
	public Emp edit2(Emp vo);
	
	public boolean edit(Emp vo);

	@Cacheable(key = "#eid", sync = true , condition="#eid<8000" , unless="#result == null")
	public Emp getEmp(long eid, String ena);
	
	@Cacheable(cacheNames = "emp")
	public Emp preEdit(long id) ;
	
	
	public Map<String,Object> list(long currentPage,int lineSize,String column,String keyWord) ;
}
