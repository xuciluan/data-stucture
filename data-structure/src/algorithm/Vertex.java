package algorithm;

/**
 * 顶点
 * @author xcl
 *
 */
public class Vertex {

	//这里是finally，表示只能修改一次
	private final String id;
	private final String name;
	
	public Vertex(String id,String name){
		this.id= id;
		this.name = name;
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	
	@Override
	public int hashCode() {
		 final int prime = 31;
		int result = 1;
		    result = prime * result + ((id == null) ? 0 : id.hashCode());
		    return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(getClass()!= obj.getClass()){
			return false;
		}
		Vertex other = (Vertex)obj;
		if(id == null){
			if(other.getId() != null){
				return false;
			}
		}
		if(!id.equals(other.getId())){
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
	
		return this.name;
	}

}
