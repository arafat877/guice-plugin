/**
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.inject.tools.ideplugin.results;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.google.inject.tools.ideplugin.ActionsHandler;

/**
 * Represents a set of results to be displayed by the {@link ResultsHandler}.  Builds a tree
 * structure of the results for display purposes.
 * 
 * @author Darren Creutz <dcreutz@gmail.com>
 */
public class Results {
	/**
	 * A Node in the tree structure of these results.
	 * 
	 * Warning: the tree is not synchronized by the Results object, the client is responsible.
	 */
	public static class Node {
    public static class ActionStringElement {
      private final String label;
      private final ActionsHandler.Action action;
      public ActionStringElement(String label) {
        this.label = label;
        this.action = new ActionsHandler.NullAction();
      }
      public ActionStringElement(String label,ActionsHandler.Action action) {
        this.label = label;
        this.action = action;
      }
      public String label() {
        return label;
      }
      public ActionsHandler.Action action() {
        return action;
      }
      @Override
      public boolean equals(Object object) {
        if (!(object instanceof ActionStringElement)) return false;
        return label.equals(((ActionStringElement)object).label()) && action.equals(((ActionStringElement)object).action());
      }
      @Override
      public int hashCode() {
        return label.hashCode();
      }
      @Override
      public String toString() {
        return label;
      }
    }
    
    public static class ActionString {
      private final List<ActionStringElement> elements;
      public ActionString() {
        elements = new ArrayList<ActionStringElement>();
      }
      public void addText(String text) {
        elements.add(new ActionStringElement(text));
      }
      public void addTextWithAction(String text,ActionsHandler.Action action) {
        elements.add(new ActionStringElement(text,action));
      }
      public List<ActionStringElement> elements() {
        return elements;
      }
      @Override
      public String toString() {
        final StringBuilder string = new StringBuilder();
        for (ActionStringElement element : elements) {
          string.append(element.label());
          string.append(" ");
        }
        return string.toString();
      }
      @Override
      public boolean equals(Object object) {
        if (!(object instanceof ActionString)) return false;
        return elements.equals(((ActionString)object).elements());
      }
      @Override
      public int hashCode() {
        return elements.hashCode();
      }
    }
    
		private final ActionString text;
		private final Set<Node> children;
		
		/**
		 * Create a new Node.
		 * 
		 * @param text the text elements to display
		 */
    //TODO: encapsulate List in an object
		public Node(ActionString text) {
			this.text = text;
			this.children = new HashSet<Node>();
		}
    
    public Node(String label) {
      this.text = new ActionString();
      this.text.addText(label);
      this.children = new HashSet<Node>();
    }
    
    public Node(String label,ActionsHandler.Action action) {
      this.text = new ActionString();
      this.text.addTextWithAction(label, action);
      this.children = new HashSet<Node>();
    }
		
		/**
		 * Return the text of this node.
		 */
		public ActionString getText() {
			return text;
		}
		
    public String getTextString() {
      return text.toString();
    }
    
		/**
		 * Add a Node as a child of this node.
		 * 
		 * @param child the node to add
		 */
		public void addChild(Node child) {
			children.add(child);
		}
		
		/**
		 * Return the children of this node.
		 * 
		 * @return the children
		 */
		public Set<Node> children() {
			return children;
		}

		/**
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Results.Node(" + getTextString() + "){");
			for (Node child : children) {
				builder.append(child.toString());
				builder.append(",");
			}
			builder.append("}");
			return builder.toString();
		}
    
    @Override
    public boolean equals(Object object) {
      if (!(object instanceof Node)) return false;
      Node node = (Node)object;
      if (!getTextString().equals(node.getTextString())) return false;
      return children().equals(node.children());
    }
    
    @Override
    public int hashCode() {
      return 1;
    }
	}
  
  
	
	private final Node root;
	
	/**
	 * Create a new Results object with the given title.
	 * 
	 * @param title the title
	 */
	public Results(String title) {
		root = new Node(title);
	}
	
	/**
	 * Return the root {@link Node} of the tree of results.
	 * 
	 * @return the root node
	 */
	public Node getRoot() {
		return root;
	}
	
	/**
	 * Notify the results object that the user cancelled the operation.
	 */
	public void userCancelled() {
		// do nothing
	}
}
