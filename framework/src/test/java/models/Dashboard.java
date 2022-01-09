package models;

import lombok.Data;

@Data
public class Dashboard {
    Files files;
    Breadcrumb [] breadcrumbs;
    int generated;
    boolean is_public;
}