package com.gcit.lms.entity;

public class Genre {

	private Integer genreId;
	private String genreName;
		
	
	/**
	 * 
	 */
	public Genre() {
	
	}


	/**
	 * @param genreId
	 * @param genreNmae
	 */
	public Genre(Integer genreId, String genreNmae) {
		this.genreId = genreId;
		this.genreName = genreNmae;
	}


	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}


	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}


	/**
	 * @return the genreNmae
	 */
	public String getGenreName() {
		return genreName;
	}


	/**
	 * @param genreNmae the genreNmae to set
	 */
	public void setGenreName(String genreNmae) {
		this.genreName = genreNmae;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  genreName;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		return true;
	}
	
	
	
	
}
