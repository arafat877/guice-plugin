/**
 * Copyright (C) 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.inject.tools.ideplugin.results;

import java.util.ArrayList;
import java.util.List;
import com.google.inject.tools.ideplugin.ActionsHandler;
import com.google.inject.tools.ideplugin.results.ActionStringBuilder.ActionString;

/**
 * Represents a set of results to be displayed by the {@link ResultsHandler}.
 * Builds a tree structure of the results for display purposes.
 * 
 * @author Darren Creutz (dcreutz@gmail.com)
 */
public class Results {
  /**
   * A Node in the tree structure of these results.
   * 
   * Warning: the tree is not synchronized by the Results object, the client is
   * responsible.
   */
  public static class Node {
    protected final ActionString text;
    protected final List<Node> children;

    /**
     * Create a new Node.
     * 
     * @param text the text elements to display
     */
    public Node(ActionString text) {
      this.text = text;
      this.children = new ArrayList<Node>();
    }

    public Node(String label, String tooltip) {
      this.text = new ActionString();
      this.text.addText(label, tooltip);
      this.children = new ArrayList<Node>();
    }

    public Node(String label, ActionsHandler.Action action, String tooltip) {
      this.text = new ActionString();
      this.text.addTextWithAction(label, action, tooltip);
      this.children = new ArrayList<Node>();
    }

    /**
     * Return the text of this node with its actions.
     */
    public ActionString getText() {
      return text;
    }

    /**
     * Return the text as a string.
     */
    public String getTextString() {
      return text.toString();
    }

    /**
     * Add a Node as a child of this node.
     */
    public void addChild(Node child) {
      children.add(child);
    }

    /**
     * Return the children of this node.
     */
    public List<Node> children() {
      return children;
    }

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
      if (!(object instanceof Node)) {
        return false;
      }
      Node node = (Node) object;
      if (!getTextString().equals(node.getTextString())) {
        return false;
      }
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
   */
  public Results(String title, String tooltip) {
    root = new Node(title, tooltip);
  }

  /**
   * Return the root {@link Node} of the tree of results.
   */
  public Node getRoot() {
    return root;
  }
  
  /**
   * Is this result set empty?
   */
  public boolean isEmpty() {
    return root.children().isEmpty();
  }

  /**
   * Notify the results object that the user cancelled the operation.
   */
  public void userCancelled() {
    // do nothing
  }
}
