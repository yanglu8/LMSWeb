package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Genre implements Serializable {
	private static final long serialVersionUID = 8112018441387702863L;

	private Integer genreId;
	private String genreName;
	//private List<Book> books;

	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId
	 *            the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}

	/**
	 * @param genreName
	 *            the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}
}
