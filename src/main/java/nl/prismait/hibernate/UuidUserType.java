// ***************************************************************************
// 
//		Copyright 2011, Prisma-IT (www.prisma-it.com)
//		All rights reserved
// 
//		$HeadURL$
//		$Id$
// 
// ***************************************************************************
package nl.prismait.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class UuidUserType implements UserType {

	public Object assemble( Serializable cached, Object owner ) throws HibernateException {
		
		return deepCopy(cached);
	}

	// UUID is immutable, so we do not copy it actually
	public Object deepCopy( Object value ) throws HibernateException {
		return (String) value;
	}

	public Serializable disassemble( Object value ) throws HibernateException {
		return (UUID) deepCopy(value);
	}

	public boolean equals( Object x, Object y ) throws HibernateException {
		return (x == y) || (x != null && y != null && (x.equals(y)));
	}


	public int hashCode( Object x ) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet( ResultSet rs, String[] names, Object owner ) throws HibernateException, SQLException {
		String str = null;
		
		if (rs.getObject(names[0]) != null) {
			str = rs.getObject(names[0]).toString();
		}
		
		return str;
	}

	public void nullSafeSet( PreparedStatement st, Object value, int index ) throws HibernateException, SQLException {
		if ( value == null ) {
			st.setNull( index, Types.OTHER );
			return;
		} else {
			st.setObject(index, value, Types.OTHER);
		}
	}

	public Object replace( Object original, Object target, Object owner ) throws HibernateException {
		return original;
	}

	public Class<String> returnedClass() {
		return String.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.OTHER };
	}

	



	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
