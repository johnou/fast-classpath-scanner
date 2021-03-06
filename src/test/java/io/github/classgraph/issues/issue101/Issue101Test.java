/*
 * This file is part of ClassGraph.
 *
 * Author: Luke Hutchison
 *
 * Hosted at: https://github.com/lukehutch/fast-classpath-scanner
 *
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Luke Hutchison
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.classgraph.issues.issue101;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class Issue101Test {
    @Test
    public void nonInheritedAnnotation() {
        final ScanResult scanResult = new ClassGraph().whitelistPackages(Issue101Test.class.getPackage().getName())
                .enableAllInfo().scan();
        assertThat(scanResult.getClassesWithAnnotation(NonInheritedAnnotation.class.getName()).getNames())
                .containsOnly(AnnotatedClass.class.getName());
    }

    @Test
    public void inheritedMetaAnnotation() {
        final ScanResult scanResult = new ClassGraph().whitelistPackages(Issue101Test.class.getPackage().getName())
                .enableAllInfo().scan();
        assertThat(scanResult.getClassesWithAnnotation(InheritedMetaAnnotation.class.getName()).getStandardClasses()
                .getNames()).containsOnly(AnnotatedClass.class.getName(), NonAnnotatedSubclass.class.getName());
    }

    @Test
    public void inheritedAnnotation() {
        final ScanResult scanResult = new ClassGraph().whitelistPackages(Issue101Test.class.getPackage().getName())
                .enableAllInfo().scan();
        assertThat(scanResult.getClassesWithAnnotation(InheritedAnnotation.class.getName()).getNames())
                .containsOnly(AnnotatedClass.class.getName(), NonAnnotatedSubclass.class.getName(),
                        AnnotatedInterface.class.getName());
    }

    //    public static void main(String[] args) throws Exception {
    //        new ClassGraph().whitelistPackages(Issue101Test.class.getPackage().getName()).enableAllInfo()
    //                .scan().generateClassGraphDotFile(new File("/tmp/graph.dot"));
    //    }
}
