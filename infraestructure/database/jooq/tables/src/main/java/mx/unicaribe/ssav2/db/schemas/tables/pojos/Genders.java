/*
 * This file is generated by jOOQ.
 */
package schemas.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Genders implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String  name;

    public Genders(Genders value) {
        this.id = value.id;
        this.name = value.name;
    }

    public Genders(
        Integer id,
        String  name
    ) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for <code>public.genders.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.genders.name</code>.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Genders (");

        sb.append(id);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
