/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
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
package com.github.jcustenborder.kafka.connect.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Timestamp;

import java.util.Date;

public class Repository implements Structable {
  @JsonProperty("owner")
  User owner;
  @JsonProperty("id")
  Long id;
  @JsonProperty("name")
  String name;
  @JsonProperty("full_name")
  String fullName;
  @JsonProperty("private")
  Boolean isPrivate;
  @JsonProperty("html_url")
  String htmlUrl;
  @JsonProperty("description")
  String description;
  @JsonProperty("fork")
  Boolean fork;
  @JsonProperty("url")
  String url;
  @JsonProperty("forks_url")
  String forksUrl;
  @JsonProperty("keys_url")
  String keysUrl;
  @JsonProperty("collaborators_url")
  String collaboratorsUrl;
  @JsonProperty("teams_url")
  String teamsUrl;
  @JsonProperty("hooks_url")
  String hooksUrl;
  @JsonProperty("issue_events_url")
  String issueEventsUrl;
  @JsonProperty("events_url")
  String eventsUrl;
  @JsonProperty("assignees_url")
  String assigneesUrl;
  @JsonProperty("branches_url")
  String branchesUrl;
  @JsonProperty("tags_url")
  String tagsUrl;
  @JsonProperty("blobs_url")
  String blobsUrl;
  @JsonProperty("git_tags_url")
  String gitTagsUrl;
  @JsonProperty("git_refs_url")
  String gitRefsUrl;
  @JsonProperty("trees_url")
  String treesUrl;
  @JsonProperty("statuses_url")
  String statusesUrl;
  @JsonProperty("languages_url")
  String languagesUrl;
  @JsonProperty("stargazers_url")
  String stargazersUrl;
  @JsonProperty("contributors_url")
  String contributorsUrl;
  @JsonProperty("subscribers_url")
  String subscribersUrl;
  @JsonProperty("subscription_url")
  String subscriptionUrl;
  @JsonProperty("commits_url")
  String commitsUrl;
  @JsonProperty("git_commits_url")
  String gitCommitsUrl;
  @JsonProperty("comments_url")
  String commentsUrl;
  @JsonProperty("issue_comment_url")
  String issueCommentUrl;
  @JsonProperty("contents_url")
  String contentsUrl;
  @JsonProperty("compare_url")
  String compareUrl;
  @JsonProperty("merges_url")
  String mergesUrl;
  @JsonProperty("archive_url")
  String archiveUrl;
  @JsonProperty("downloads_url")
  String downloadsUrl;
  @JsonProperty("issues_url")
  String issuesUrl;
  @JsonProperty("pulls_url")
  String pullsUrl;
  @JsonProperty("milestones_url")
  String milestonesUrl;
  @JsonProperty("notifications_url")
  String notificationsUrl;
  @JsonProperty("labels_url")
  String labelsUrl;
  @JsonProperty("releases_url")
  String releasesUrl;
  @JsonProperty("created_at")
  Date createdAt;
  @JsonProperty("updated_at")
  Date updatedAt;
  @JsonProperty("pushed_at")
  Date pushedAt;
  @JsonProperty("git_url")
  String gitUrl;
  @JsonProperty("ssh_url")
  String sshUrl;
  @JsonProperty("clone_url")
  String cloneUrl;
  @JsonProperty("svn_url")
  String svnUrl;
  @JsonProperty("homepage")
  String homepage;
  @JsonProperty("mirror_url")
  String mirrorUrl;
  @JsonProperty("language")
  String language;
  @JsonProperty("default_branch")
  String defaultBranch;

  @JsonProperty("size")
  Long size;
  @JsonProperty("stargazers_count")
  Long stargazersCount;
  @JsonProperty("watchers_count")
  Long watchersCount;
  @JsonProperty("has_issues")
  Boolean hasIssues;
  @JsonProperty("has_downloads")
  Boolean hasDownloads;
  @JsonProperty("has_wiki")
  Boolean hasWiki;
  @JsonProperty("has_pages")
  Boolean hasPages;
  @JsonProperty("forks_count")
  Long forksCount;
  @JsonProperty("open_issues_count")
  Long openIssuesCount;
  @JsonProperty("forks")
  Long forks;
  @JsonProperty("open_issues")
  Long openIssues;
  @JsonProperty("watchers")
  Long watchers;

  public User owner() {
    return owner;
  }

  public void owner(User owner) {
    this.owner = owner;
  }

  public Long id() {
    return id;
  }

  public void id(Long id) {
    this.id = id;
  }

  public String name() {
    return name;
  }

  public void name(String name) {
    this.name = name;
  }

  public String fullName() {
    return fullName;
  }

  public void fullName(String fullName) {
    this.fullName = fullName;
  }

  public Boolean isPrivate() {
    return isPrivate;
  }

  public void isPrivate(Boolean value) {
    this.isPrivate = value;
  }

  public String htmlUrl() {
    return htmlUrl;
  }

  public void htmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String description() {
    return description;
  }

  public void description(String description) {
    this.description = description;
  }

  public Boolean fork() {
    return fork;
  }

  public void fork(Boolean fork) {
    this.fork = fork;
  }

  public String url() {
    return url;
  }

  public void url(String url) {
    this.url = url;
  }

  public String forksUrl() {
    return forksUrl;
  }

  public void forksUrl(String forksUrl) {
    this.forksUrl = forksUrl;
  }

  public String keysUrl() {
    return keysUrl;
  }

  public void keysUrl(String keysUrl) {
    this.keysUrl = keysUrl;
  }

  public String collaboratorsUrl() {
    return collaboratorsUrl;
  }

  public void collaboratorsUrl(String collaboratorsUrl) {
    this.collaboratorsUrl = collaboratorsUrl;
  }

  public String teamsUrl() {
    return teamsUrl;
  }

  public void teamsUrl(String teamsUrl) {
    this.teamsUrl = teamsUrl;
  }

  public String hooksUrl() {
    return hooksUrl;
  }

  public void hooksUrl(String hooksUrl) {
    this.hooksUrl = hooksUrl;
  }

  public String issueEventsUrl() {
    return issueEventsUrl;
  }

  public void issueEventsUrl(String issueEventsUrl) {
    this.issueEventsUrl = issueEventsUrl;
  }

  public String eventsUrl() {
    return eventsUrl;
  }

  public void eventsUrl(String eventsUrl) {
    this.eventsUrl = eventsUrl;
  }

  public String assigneesUrl() {
    return assigneesUrl;
  }

  public void assigneesUrl(String assigneesUrl) {
    this.assigneesUrl = assigneesUrl;
  }

  public String branchesUrl() {
    return branchesUrl;
  }

  public void branchesUrl(String branchesUrl) {
    this.branchesUrl = branchesUrl;
  }

  public String tagsUrl() {
    return tagsUrl;
  }

  public void tagsUrl(String tagsUrl) {
    this.tagsUrl = tagsUrl;
  }

  public String blobsUrl() {
    return blobsUrl;
  }

  public void blobsUrl(String blobsUrl) {
    this.blobsUrl = blobsUrl;
  }

  public String gitTagsUrl() {
    return gitTagsUrl;
  }

  public void gitTagsUrl(String gitTagsUrl) {
    this.gitTagsUrl = gitTagsUrl;
  }

  public String gitRefsUrl() {
    return gitRefsUrl;
  }

  public void gitRefsUrl(String gitRefsUrl) {
    this.gitRefsUrl = gitRefsUrl;
  }

  public String treesUrl() {
    return treesUrl;
  }

  public void treesUrl(String treesUrl) {
    this.treesUrl = treesUrl;
  }

  public String statusesUrl() {
    return statusesUrl;
  }

  public void statusesUrl(String statusesUrl) {
    this.statusesUrl = statusesUrl;
  }

  public String languagesUrl() {
    return languagesUrl;
  }

  public void languagesUrl(String languagesUrl) {
    this.languagesUrl = languagesUrl;
  }

  public String stargazersUrl() {
    return stargazersUrl;
  }

  public void stargazersUrl(String stargazersUrl) {
    this.stargazersUrl = stargazersUrl;
  }

  public String contributorsUrl() {
    return contributorsUrl;
  }

  public void contributorsUrl(String contributorsUrl) {
    this.contributorsUrl = contributorsUrl;
  }

  public String subscribersUrl() {
    return subscribersUrl;
  }

  public void subscribersUrl(String subscribersUrl) {
    this.subscribersUrl = subscribersUrl;
  }

  public String subscriptionUrl() {
    return subscriptionUrl;
  }

  public void subscriptionUrl(String subscriptionUrl) {
    this.subscriptionUrl = subscriptionUrl;
  }

  public String commitsUrl() {
    return commitsUrl;
  }

  public void commitsUrl(String commitsUrl) {
    this.commitsUrl = commitsUrl;
  }

  public String gitCommitsUrl() {
    return gitCommitsUrl;
  }

  public void gitCommitsUrl(String gitCommitsUrl) {
    this.gitCommitsUrl = gitCommitsUrl;
  }

  public String commentsUrl() {
    return commentsUrl;
  }

  public void commentsUrl(String commentsUrl) {
    this.commentsUrl = commentsUrl;
  }

  public String issueCommentUrl() {
    return issueCommentUrl;
  }

  public void issueCommentUrl(String issueCommentUrl) {
    this.issueCommentUrl = issueCommentUrl;
  }

  public String contentsUrl() {
    return contentsUrl;
  }

  public void contentsUrl(String contentsUrl) {
    this.contentsUrl = contentsUrl;
  }

  public String compareUrl() {
    return compareUrl;
  }

  public void compareUrl(String compareUrl) {
    this.compareUrl = compareUrl;
  }

  public String mergesUrl() {
    return mergesUrl;
  }

  public void mergesUrl(String mergesUrl) {
    this.mergesUrl = mergesUrl;
  }

  public String archiveUrl() {
    return archiveUrl;
  }

  public void archiveUrl(String archiveUrl) {
    this.archiveUrl = archiveUrl;
  }

  public String downloadsUrl() {
    return downloadsUrl;
  }

  public void downloadsUrl(String downloadsUrl) {
    this.downloadsUrl = downloadsUrl;
  }

  public String issuesUrl() {
    return issuesUrl;
  }

  public void issuesUrl(String issuesUrl) {
    this.issuesUrl = issuesUrl;
  }

  public String pullsUrl() {
    return pullsUrl;
  }

  public void pullsUrl(String pullsUrl) {
    this.pullsUrl = pullsUrl;
  }

  public String milestonesUrl() {
    return milestonesUrl;
  }

  public void milestonesUrl(String milestonesUrl) {
    this.milestonesUrl = milestonesUrl;
  }

  public String notificationsUrl() {
    return notificationsUrl;
  }

  public void notificationsUrl(String notificationsUrl) {
    this.notificationsUrl = notificationsUrl;
  }

  public String labelsUrl() {
    return labelsUrl;
  }

  public void labelsUrl(String labelsUrl) {
    this.labelsUrl = labelsUrl;
  }

  public String releasesUrl() {
    return releasesUrl;
  }

  public void releasesUrl(String releasesUrl) {
    this.releasesUrl = releasesUrl;
  }

  public Date createdAt() {
    return createdAt;
  }

  public void createdAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date updatedAt() {
    return updatedAt;
  }

  public void updatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Date pushedAt() {
    return pushedAt;
  }

  public void pushedAt(Date pushedAt) {
    this.pushedAt = pushedAt;
  }

  public String gitUrl() {
    return gitUrl;
  }

  public void gitUrl(String gitUrl) {
    this.gitUrl = gitUrl;
  }

  public String sshUrl() {
    return sshUrl;
  }

  public void sshUrl(String sshUrl) {
    this.sshUrl = sshUrl;
  }

  public String cloneUrl() {
    return cloneUrl;
  }

  public void cloneUrl(String cloneUrl) {
    this.cloneUrl = cloneUrl;
  }

  public String svnUrl() {
    return svnUrl;
  }

  public void svnUrl(String svnUrl) {
    this.svnUrl = svnUrl;
  }

  public String homepage() {
    return homepage;
  }

  public void homepage(String homepage) {
    this.homepage = homepage;
  }

  public String mirrorUrl() {
    return mirrorUrl;
  }

  public void mirrorUrl(String mirrorUrl) {
    this.mirrorUrl = mirrorUrl;
  }

  public String language() {
    return language;
  }

  public void language(String language) {
    this.language = language;
  }

  public String defaultBranch() {
    return defaultBranch;
  }

  public void defaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
  }

  public Long size() {
    return size;
  }

  public void size(Long size) {
    this.size = size;
  }

  public Long stargazersCount() {
    return stargazersCount;
  }

  public void stargazersCount(Long stargazersCount) {
    this.stargazersCount = stargazersCount;
  }

  public Long watchersCount() {
    return watchersCount;
  }

  public void watchersCount(Long watchersCount) {
    this.watchersCount = watchersCount;
  }

  public Boolean hasIssues() {
    return hasIssues;
  }

  public void hasIssues(Boolean hasIssues) {
    this.hasIssues = hasIssues;
  }

  public Boolean hasDownloads() {
    return hasDownloads;
  }

  public void hasDownloads(Boolean hasDownloads) {
    this.hasDownloads = hasDownloads;
  }

  public Boolean hasWiki() {
    return hasWiki;
  }

  public void hasWiki(Boolean hasWiki) {
    this.hasWiki = hasWiki;
  }

  public Boolean hasPages() {
    return hasPages;
  }

  public void hasPages(Boolean hasPages) {
    this.hasPages = hasPages;
  }

  public Long forksCount() {
    return forksCount;
  }

  public void forksCount(Long forksCount) {
    this.forksCount = forksCount;
  }

  public Long openIssuesCount() {
    return openIssuesCount;
  }

  public void openIssuesCount(Long openIssuesCount) {
    this.openIssuesCount = openIssuesCount;
  }

  public Long forks() {
    return forks;
  }

  public void forks(Long forks) {
    this.forks = forks;
  }

  public Long openIssues() {
    return openIssues;
  }

  public void openIssues(Long openIssues) {
    this.openIssues = openIssues;
  }

  public Long watchers() {
    return watchers;
  }

  public void watchers(Long watchers) {
    this.watchers = watchers;
  }

  public static final Schema VALUE_SCHEMA;

  static {
    VALUE_SCHEMA = SchemaBuilder.struct()
        .name(Repository.class.getName())
        .optional()
        .field("owner", User.VALUE_SCHEMA)
        .field("id", SchemaBuilder.int64().optional().build())
        .field("name", SchemaBuilder.string().optional().build())
        .field("full_name", SchemaBuilder.string().optional().build())
        .field("private", SchemaBuilder.bool().optional().build())
        .field("html_url", SchemaBuilder.string().optional().build())
        .field("description", SchemaBuilder.string().optional().build())
        .field("fork", SchemaBuilder.bool().optional().build())
        .field("url", SchemaBuilder.string().optional().build())
        .field("forks_url", SchemaBuilder.string().optional().build())
        .field("keys_url", SchemaBuilder.string().optional().build())
        .field("collaborators_url", SchemaBuilder.string().optional().build())
        .field("teams_url", SchemaBuilder.string().optional().build())
        .field("hooks_url", SchemaBuilder.string().optional().build())
        .field("issue_events_url", SchemaBuilder.string().optional().build())
        .field("events_url", SchemaBuilder.string().optional().build())
        .field("assignees_url", SchemaBuilder.string().optional().build())
        .field("branches_url", SchemaBuilder.string().optional().build())
        .field("tags_url", SchemaBuilder.string().optional().build())
        .field("blobs_url", SchemaBuilder.string().optional().build())
        .field("git_tags_url", SchemaBuilder.string().optional().build())
        .field("git_refs_url", SchemaBuilder.string().optional().build())
        .field("trees_url", SchemaBuilder.string().optional().build())
        .field("statuses_url", SchemaBuilder.string().optional().build())
        .field("languages_url", SchemaBuilder.string().optional().build())
        .field("stargazers_url", SchemaBuilder.string().optional().build())
        .field("contributors_url", SchemaBuilder.string().optional().build())
        .field("subscribers_url", SchemaBuilder.string().optional().build())
        .field("subscription_url", SchemaBuilder.string().optional().build())
        .field("commits_url", SchemaBuilder.string().optional().build())
        .field("git_commits_url", SchemaBuilder.string().optional().build())
        .field("comments_url", SchemaBuilder.string().optional().build())
        .field("issue_comment_url", SchemaBuilder.string().optional().build())
        .field("contents_url", SchemaBuilder.string().optional().build())
        .field("compare_url", SchemaBuilder.string().optional().build())
        .field("merges_url", SchemaBuilder.string().optional().build())
        .field("archive_url", SchemaBuilder.string().optional().build())
        .field("downloads_url", SchemaBuilder.string().optional().build())
        .field("issues_url", SchemaBuilder.string().optional().build())
        .field("pulls_url", SchemaBuilder.string().optional().build())
        .field("milestones_url", SchemaBuilder.string().optional().build())
        .field("notifications_url", SchemaBuilder.string().optional().build())
        .field("labels_url", SchemaBuilder.string().optional().build())
        .field("releases_url", SchemaBuilder.string().optional().build())
        .field("created_at", Timestamp.builder().optional().build())
        .field("updated_at", Timestamp.builder().optional().build())
        .field("pushed_at", Timestamp.builder().optional().build())
        .field("git_url", SchemaBuilder.string().optional().build())
        .field("ssh_url", SchemaBuilder.string().optional().build())
        .field("clone_url", SchemaBuilder.string().optional().build())
        .field("svn_url", SchemaBuilder.string().optional().build())
        .field("homepage", SchemaBuilder.string().optional().build())
        .field("mirror_url", SchemaBuilder.string().optional().build())
        .field("language", SchemaBuilder.string().optional().build())
        .field("default_branch", SchemaBuilder.string().optional().build())
        .field("size", SchemaBuilder.int64().optional().build())
        .field("stargazers_count", SchemaBuilder.int64().optional().build())
        .field("watchers_count", SchemaBuilder.int64().optional().build())
        .field("has_issues", SchemaBuilder.bool().optional().build())
        .field("has_downloads", SchemaBuilder.bool().optional().build())
        .field("has_wiki", SchemaBuilder.bool().optional().build())
        .field("has_pages", SchemaBuilder.bool().optional().build())
        .field("forks_count", SchemaBuilder.int64().optional().build())
        .field("open_issues_count", SchemaBuilder.int64().optional().build())
        .field("forks", SchemaBuilder.int64().optional().build())
        .field("open_issues", SchemaBuilder.int64().optional().build())
        .field("watchers", SchemaBuilder.int64().optional().build())
        .build();
  }

  @Override
  public Schema keySchema() {
    return null;
  }

  @Override
  public Struct keyStruct() {
    return null;
  }

  @Override
  public Schema valueSchema() {
    return VALUE_SCHEMA;
  }

  @Override
  public Struct valueStruct() {
    return new Struct(VALUE_SCHEMA)
        .put("owner", null != this.owner ? this.owner.valueStruct() : null)
        .put("id", this.id)
        .put("name", this.name)
        .put("full_name", this.fullName)
        .put("private", this.isPrivate)
        .put("html_url", this.htmlUrl)
        .put("description", this.description)
        .put("fork", this.fork)
        .put("url", this.url)
        .put("forks_url", this.forksUrl)
        .put("keys_url", this.keysUrl)
        .put("collaborators_url", this.collaboratorsUrl)
        .put("teams_url", this.teamsUrl)
        .put("hooks_url", this.hooksUrl)
        .put("issue_events_url", this.issueEventsUrl)
        .put("events_url", this.eventsUrl)
        .put("assignees_url", this.assigneesUrl)
        .put("branches_url", this.branchesUrl)
        .put("tags_url", this.tagsUrl)
        .put("blobs_url", this.blobsUrl)
        .put("git_tags_url", this.gitTagsUrl)
        .put("git_refs_url", this.gitRefsUrl)
        .put("trees_url", this.treesUrl)
        .put("statuses_url", this.statusesUrl)
        .put("languages_url", this.languagesUrl)
        .put("stargazers_url", this.stargazersUrl)
        .put("contributors_url", this.contributorsUrl)
        .put("subscribers_url", this.subscribersUrl)
        .put("subscription_url", this.subscriptionUrl)
        .put("commits_url", this.commitsUrl)
        .put("git_commits_url", this.gitCommitsUrl)
        .put("comments_url", this.commentsUrl)
        .put("issue_comment_url", this.issueCommentUrl)
        .put("contents_url", this.contentsUrl)
        .put("compare_url", this.compareUrl)
        .put("merges_url", this.mergesUrl)
        .put("archive_url", this.archiveUrl)
        .put("downloads_url", this.downloadsUrl)
        .put("issues_url", this.issuesUrl)
        .put("pulls_url", this.pullsUrl)
        .put("milestones_url", this.milestonesUrl)
        .put("notifications_url", this.notificationsUrl)
        .put("labels_url", this.labelsUrl)
        .put("releases_url", this.releasesUrl)
        .put("created_at", this.createdAt)
        .put("updated_at", this.updatedAt)
        .put("pushed_at", this.pushedAt)
        .put("git_url", this.gitUrl)
        .put("ssh_url", this.sshUrl)
        .put("clone_url", this.cloneUrl)
        .put("svn_url", this.svnUrl)
        .put("homepage", this.homepage)
        .put("mirror_url", this.mirrorUrl)
        .put("language", this.language)
        .put("default_branch", this.defaultBranch)
        .put("size", this.size)
        .put("stargazers_count", this.stargazersCount)
        .put("watchers_count", this.watchersCount)
        .put("has_issues", this.hasIssues)
        .put("has_downloads", this.hasDownloads)
        .put("has_wiki", this.hasWiki)
        .put("has_pages", this.hasPages)
        .put("forks_count", this.forksCount)
        .put("open_issues_count", this.openIssuesCount)
        .put("forks", this.forks)
        .put("open_issues", this.openIssues)
        .put("watchers", this.watchers);
  }

  @Override
  public Long timestamp() {
    return null;
  }
}
