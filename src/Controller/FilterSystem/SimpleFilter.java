/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FilterSystem;

/**
 * 
 * @author F.Rossi
 */
public abstract class SimpleFilter extends Filter {

	protected Object element;

	public SimpleFilter(Object obj) {
		this.setElement(obj);
	}

	protected Object getElement() {
		return element;
	}

	protected void setElement(Object elem) {
		this.element = elem;
	}

}