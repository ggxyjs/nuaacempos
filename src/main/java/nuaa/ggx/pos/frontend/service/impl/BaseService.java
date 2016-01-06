package nuaa.ggx.pos.frontend.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import nuaa.ggx.pos.frontend.dao.interfaces.IBaseDao;
import nuaa.ggx.pos.frontend.service.interfaces.IBaseService;

import org.apache.log4j.Logger;

public class BaseService<T> implements IBaseService<T>{
	
	protected Class<T> entityClass;
	protected IBaseDao<T> baseDao;
	
	private static Logger log = Logger.getLogger(BaseService.class);
	
	@SuppressWarnings("unchecked")
	public BaseService(IBaseDao<T> baseDao) {
        this.entityClass = (Class<T>)getSuperClassGenricType(getClass(), 0);
        this.baseDao = baseDao;
	}
	
	@SuppressWarnings("unchecked")  
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {  
          
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。  
        Type genType = clazz.getGenericSuperclass();  
  
        if (!(genType instanceof ParameterizedType)) {  
           return Object.class;  
        }  
        //返回表示此类型实际类型参数的 Type 对象的数组。  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
  
        if (index >= params.length || index < 0) {  
                     return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
              return Object.class;  
        }  
  
        return (Class) params[index];  
    }  
	
	@Override
	public T getById(Integer id) {		
		return baseDao.getById(id);
	}

	@Override
	public T loadById(Integer id) {
		return baseDao.loadById(id);
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		this.delete(this.getById(id));
	}

	@Override
	public void delete(T t) {
		baseDao.delete(t);
	}

	@Override
	public void update(T t) {
		baseDao.attachDirty(t);
	}

	@Override
	public T merge(T t) {
		return baseDao.merge(t);
	}
    

}
