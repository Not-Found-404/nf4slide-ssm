package com.qtu404.folder.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String title;
    private String key;
    // private Integer parent;
    private boolean isLoading = false;
    private List<TreeNode> children;

    public TreeNode() {
    }

    public static TreeNode createNzTreeNode(Folder folder) {
        TreeNode treeNode = new TreeNode();
        treeNode.setKey(String.valueOf(folder.getFolderId()));
        treeNode.setTitle(folder.getFolderName());
        treeNode.setChildren(getChildren(folder));
        return treeNode;
    }

    private static List<TreeNode> getChildren(Folder folder) {
        List<TreeNode> treeNodes = new ArrayList<>(5);
        if (folder == null || folder.getChild() == null || folder.getChild().size() == 0)
            return null;
        for (Folder child : folder.getChild()) {
            TreeNode treeNode = new TreeNode();
            treeNode.setKey(String.valueOf(child.getFolderId()));
            treeNode.setTitle(child.getFolderName());
            treeNode.setChildren(getChildren(child));
            treeNodes.add(treeNode);
        }
        return treeNodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

}
