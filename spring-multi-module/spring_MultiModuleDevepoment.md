# Creating a Multi Module Project

Creating a Multi Module Project

## What You Will Build
You will set up a library jar that exposes a service for simple “Hello, World” messages and then include the service in a web application that uses the library as a dependency.

## What you need

### How to complete this guide

Like most Spring Getting Started guides, you can start from scratch and complete each step or you can bypass basic setup steps that are already familiar to you. Either way, you end up with working code.

To start from scratch, move on to Create a Root Project.

To skip the basics, do the following:

Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-multi-module.git

cd into gs-multi-module/initial

Jump ahead to Create the Library Project.

When you finish, you can check your results against the code in gs-multi-module/complete.

First you set up a basic build script. You can use any build system you like when building apps with Spring, but the code you need to work with Gradle and Maven is included here. If you’re not familiar with either, refer to Building Java Projects with Gradle or Building Java Projects with Maven.

## Create a Root Project
Create a Root Project